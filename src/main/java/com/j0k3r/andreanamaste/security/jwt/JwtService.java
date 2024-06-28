package com.j0k3r.andreanamaste.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private Environment environment;

    public String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("SECRET_KEY_TOKEN"));
        return JWT.create()
                .withIssuer("andrea-namaste")
                .withSubject(username)
                .sign(algorithm);
    }

    public String generateRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("SECRET_KEY_REFRESH_TOKEN"));
        return JWT.create()
                .withIssuer("andrea-namaste-refresh")
                .withSubject(username)
                .sign(algorithm);
    }

    public String validateAndGetSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("SECRET_KEY_TOKEN"));
        return JWT.require(algorithm)
                .withIssuer("andrea-namaste")
                .build()
                .verify(token)
                .getSubject();
    }

    public String validateAndGetSubjectRefreshToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("SECRET_KEY_REFRESH_TOKEN"));
        return JWT.require(algorithm)
                .withIssuer("andrea-namaste-refresh")
                .build()
                .verify(token)
                .getSubject();
    }

}
