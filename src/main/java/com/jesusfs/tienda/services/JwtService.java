package com.jesusfs.tienda.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jesusfs.tienda.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.TemporalUnit;

@Service
@Slf4j
public class JwtService {
    @Value("{jwt.private.key}")
    private String privateKey;

    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            return JWT.create()
                    .withIssuer("tienda-api")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("tienda-api")
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception.toString());
        }
    }

    private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(3600);
    }
}
