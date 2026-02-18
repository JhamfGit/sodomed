package com.sodemed.configurationsjwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sodemed.utils.response.ErrorResponse;
import com.sodemed.utils.response.ResponseData;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/api/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/medication/resource/**"
    };

    private static final String[] ADMIN_MEDICATION_MATCHERS = {
            "/api/medication/partial/update/",
            "/api/medication/download-data",
            "/api/medication/take/{id}",
            "/api/medication/fetch/**",
            "/api/medication/fetch",
            "/api/medication/movements/{id}"
    };

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Define the password encoder bean
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/auth/update/**").authenticated()
                        .requestMatchers(PUBLIC_MATCHERS).permitAll() // Acceso público a rutas permitidas
                        .requestMatchers("/api/client/**").hasAnyAuthority("admin:sodemed", "clients:sodemed")
                        .requestMatchers("/api/employee/**").hasAnyAuthority("admin:sodemed", "employees:sodemed")
                        .requestMatchers("/api/role/**").hasAnyAuthority("admin:sodemed", "roles:sodemed")
                        .requestMatchers(ADMIN_MEDICATION_MATCHERS)
                        .hasAnyAuthority("admin:sodemed", "medications:sodemed")
                        .anyRequest().authenticated() // Autenticación requerida para el resto
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Permite iframes para la misma
                                                                                        // fuente
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsConfigurationSource())
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            sendErrorResponse(response,
                                    "Sin autorización, no tiene los permisos necesarios para acceder al recurso", 401);
                        }));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(new String[] { "*" }));
        configuration.setAllowedMethods(Arrays.asList(new String[] { "GET", "POST", "PUT", "DELETE" }));
        configuration.setAllowedHeaders(Arrays.asList(new String[] { "Authorization", "Content-Type" }));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
