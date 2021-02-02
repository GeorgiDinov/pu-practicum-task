package com.practicum.pu.georgidinov.shoppinglist.security;

import com.practicum.pu.georgidinov.shoppinglist.security.jwt.JwtPropertyHolder;
import com.practicum.pu.georgidinov.shoppinglist.security.jwt.JwtTokenVerifier;
import com.practicum.pu.georgidinov.shoppinglist.security.jwt.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.USER;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //== fields ==
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtPropertyHolder jwtPropertyHolder;

    //== constructors ==
    @Autowired
    public SecurityConfig(DaoAuthenticationProvider daoAuthenticationProvider,
                          JwtPropertyHolder jwtPropertyHolder) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.jwtPropertyHolder = jwtPropertyHolder;
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
                .headers().frameOptions().disable()//to access H2 from browser
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), this.jwtPropertyHolder))
                .addFilterAfter(new JwtTokenVerifier(this.jwtPropertyHolder), JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/static/**", "/", "/register").permitAll()
                .antMatchers("/items/**").hasRole(USER.name())
                .anyRequest()
                .authenticated();
    }

}