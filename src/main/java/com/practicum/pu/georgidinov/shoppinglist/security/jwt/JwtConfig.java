package com.practicum.pu.georgidinov.shoppinglist.security.jwt;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.crypto.SecretKey;


@Configuration
@ComponentScan(basePackages = "com.practicum.pu.georgidinov.shoppinglist.security")
@PropertySource("classpath:application.properties")
public class JwtConfig {

    //== fields ==
    @Value("${application.jwt.secretKey:secret123*}")
    private String secretKey;

    @Value("${application.jwt.tokenPrefix:Bearer }")
    private String tokenPrefix;

    @Value("${application.jwt.tokenExpirationAfterDays:5}")
    private String tokenExpirationAfterDays;


    //== bean methods ==
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes());
    }

    @Bean
    @TokenPrefix
    public String tokenPrefix() {
        return this.tokenPrefix;
    }

    @Bean
    @TokenExpirationAfterDays
    public String tokenExpirationAfterDays() {
        return this.tokenExpirationAfterDays;
    }

}