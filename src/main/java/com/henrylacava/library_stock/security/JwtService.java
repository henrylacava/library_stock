package com.henrylacava.library_stock.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {
    private final Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET);

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        return JWT.create()
                .withSubject(email)
                .withIssuer("library_stock")
                .withIssuedAt(currentDate)
                .withExpiresAt(expireDate)
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
}
