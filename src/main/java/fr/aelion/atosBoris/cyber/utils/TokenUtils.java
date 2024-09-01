package fr.aelion.atosBoris.cyber.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.aelion.atosBoris.cyber.models.entities.User;

import java.time.Instant;

public class TokenUtils {
    public static String generateJWT(User u) {
        return JWT.create()
                .withClaim("email", u.getEmail())
                .withClaim("datetime", Instant.now())
                // TODO: store + encrypt in properties
                .sign(Algorithm.HMAC512("SECRET"));
    }

    public static DecodedJWT decodeJWT(String token) {
        // TODO: handle expiration, renewal, verification exception
        return JWT.require(Algorithm.HMAC512("SECRET")).build().verify(token);
    }
}
