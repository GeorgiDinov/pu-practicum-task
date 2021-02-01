package com.practicum.pu.georgidinov.shoppinglist.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Getter
@Component
public class JwtPropertyHolder {

    //== fields ==
    private final String authorizationHeader = HttpHeaders.AUTHORIZATION;
    private final SecretKey secretKey;
    private final String tokenPrefix;
    private final Long tokenExpirationAfterDays;

    //== constructors ==
    @Autowired
    public JwtPropertyHolder(SecretKey secretKey,
                             @TokenPrefix String tokenPrefix,
                             @TokenExpirationAfterDays Long tokenExpirationAfterDays) {
        this.secretKey = secretKey;
        this.tokenPrefix = tokenPrefix;
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

}