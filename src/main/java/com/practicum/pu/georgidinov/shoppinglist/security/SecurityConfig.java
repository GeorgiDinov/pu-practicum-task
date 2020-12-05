package com.practicum.pu.georgidinov.shoppinglist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.USER;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //== fields ==
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    //== constructors ==
    @Autowired
    public SecurityConfig(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }


    //== public methods ==
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
//                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").hasRole(USER.name())
                .anyRequest()
                .authenticated();
    }


}