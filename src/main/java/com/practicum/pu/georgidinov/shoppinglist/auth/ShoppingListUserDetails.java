package com.practicum.pu.georgidinov.shoppinglist.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class ShoppingListUserDetails implements UserDetails {

    //== constants ==
    private static final boolean IS_TRUE = true;

    //== fields ==
    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;

    private final boolean isAccountNonExpired = IS_TRUE;//todo when basic flow is up and running
    private final boolean isAccountNonLocked = IS_TRUE;//todo when basic flow is up and running
    private final boolean isCredentialsNonExpired = IS_TRUE;//todo when basic flow is up and running
    private final boolean isEnabled = IS_TRUE;//todo when basic flow is up and running


    //== constructors ==
    public ShoppingListUserDetails(String username, String password,
                                   Set<? extends GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }


    //== public methods ==
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return IS_TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return IS_TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return IS_TRUE;
    }

    @Override
    public boolean isEnabled() {
        return IS_TRUE;
    }
}