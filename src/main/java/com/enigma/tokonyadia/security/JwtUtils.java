package com.enigma.tokonyadia.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {
    @Value("${tokonyadia.jwt.secret}")
    private String jwtSecret;

    @Value("${tokonyadia.jwt.expiration}")
    private Long jwtExpiration;

    public String getEmailByToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(new Date((System.currentTimeMillis() + jwtExpiration)))
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT token {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("JWT token is expired {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("Unsupported JWT token {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims string is empty {}", exception.getMessage());
        }
        return false;
    }
}
