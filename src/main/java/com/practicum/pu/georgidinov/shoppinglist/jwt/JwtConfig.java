package com.practicum.pu.georgidinov.shoppinglist.jwt;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.practicum.pu.georgidinov.shoppinglist")
@PropertySource("classpath:config/jwt.properties")
public class JwtConfig {

    //== fields ==
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.tokenExpirationAfterDays}")
    private Integer tokenExpirationAfterDays;

    //== constructors ==
    public JwtConfig() {
    }

    //== public methods ==
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}