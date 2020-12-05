package com.practicum.pu.georgidinov.shoppinglist.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {

    //== fields ==
    private final JwtConfig jwtConfig;

    //== constructors ==
    @Autowired
    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    //== bean methods ==
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}