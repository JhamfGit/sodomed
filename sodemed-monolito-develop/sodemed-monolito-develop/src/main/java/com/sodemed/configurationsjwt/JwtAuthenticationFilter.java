package com.sodemed.configurationsjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sodemed.utils.response.ErrorResponse;
import com.sodemed.utils.response.ResponseData;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, UsernameNotFoundException {
        String requestURI = request.getRequestURI();

        // Allow access to public endpoints without authentication
        if (requestURI.startsWith("/api/medication/resource")
                || (requestURI.startsWith("/api/auth") && !requestURI.startsWith("/api/auth/update"))
                || requestURI.startsWith("/h2-console")) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                username = jwtUtils.extractUsername(token);
            } catch (ExpiredJwtException e) {
                sendErrorResponse(response, "Tiempo de sesión terminado", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (JwtException e) {
                sendErrorResponse(response, "Autorización invalida", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (Exception e) {
                sendErrorResponse(response, "Fallo de seguridad" + e.getMessage(),
                        HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            sendErrorResponse(response, "No se encontró la sesión", HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && jwtUtils.validateToken(token, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String message, int status) throws IOException {
        ResponseData<ErrorResponse> errorResponse = new ResponseData<ErrorResponse>(status,
                new ErrorResponse(message, message), message, false);

        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.getWriter().write(jsonResponse);
    }
}
