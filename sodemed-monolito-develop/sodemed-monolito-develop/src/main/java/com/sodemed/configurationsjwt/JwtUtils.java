package com.sodemed.configurationsjwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.sodemed.models.users.User;
import com.sodemed.models.users.enums.UserType;

@Component
public class JwtUtils {
    private final SecretKey secretKey;

    @Value("${jwt.expiration.client}")
    private long expirationClient;

    @Value("${jwt.expiration.employee}")
    private long expirationEmployee;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(claims.get("time").toString())))
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public String generateClaims(String username, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("time", user.getUserType() != UserType.employee ? expirationClient : expirationEmployee);
        return generateToken(username, claims);
    }

    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    public Integer extractUserId(String bearerToken) {
        String token = bearerToken.substring(7);
        Claims claims = parseClaims(token);
        return claims.get("userId", Integer.class); // Extrae el userId como Integer
    }

}
