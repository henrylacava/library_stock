package com.henrylacava.library_stock.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtService {
    private final Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET);

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();

        return JWT.create()
                .withSubject(email)
                .withIssuer("library_stock")
                .withExpiresAt(this.genExpirationDate())
                .sign(this.algorithm);
    }

    public String getEmailFromJwt(String token) {
        return JWT.require(this.algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(this.algorithm)
                    .withIssuer("library_stock")
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
