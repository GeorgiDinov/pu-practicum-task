package com.practicum.pu.georgidinov.shoppinglist.security;

import com.practicum.pu.georgidinov.shoppinglist.auth.ShoppingListUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationProviderConfig {

    //== fields ==
    private final PasswordEncoder passwordEncoder;
    private final ShoppingListUserDetailsService shoppingListUserDetailsService;

    //== constructors ==
    @Autowired
    public AuthenticationProviderConfig(PasswordEncoder passwordEncoder,
                                        ShoppingListUserDetailsService shoppingListUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.shoppingListUserDetailsService = shoppingListUserDetailsService;
    }

    //== bean methods ==
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(shoppingListUserDetailsService);
        return provider;
    }
}
