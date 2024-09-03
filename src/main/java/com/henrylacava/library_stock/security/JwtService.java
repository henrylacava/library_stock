package com.henrylacava.library_stock.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication) {
        String name = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET);
        return JWT.create()
                .withSubject(name)
                .withIssuedAt(currentDate)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    public boolean validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET);

        try {
            JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return true;
        } catch (JWTVerificationException exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
