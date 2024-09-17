package com.app.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private final String secretKey;

    public JWTService(@Value("${spring.properties.jwt.key}") String _secretKey){
        secretKey=_secretKey;
    }

    public String getSubject(String token){
        return this.
                decodeToken(token).
                getSubject();
    }
    public Boolean verifyToken(String token){
        return this.
                decodeToken(token).
                getExpiresAt().
                after(new Date(System.currentTimeMillis()));
    }
    private DecodedJWT decodeToken(String token){
        Algorithm verifyingAlgorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(verifyingAlgorithm)
                .withIssuer("auth0")
                .build()
                .verify(token);
    }
    public String generateToken(UserDetails user){
        Integer duration = 1000*60*60;
        Algorithm signingAlgorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(signingAlgorithm);
    }
}
