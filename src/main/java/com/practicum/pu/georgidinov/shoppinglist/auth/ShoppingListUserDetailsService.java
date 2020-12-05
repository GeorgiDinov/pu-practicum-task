package com.practicum.pu.georgidinov.shoppinglist.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListUserDetailsService implements UserDetailsService {

    //== fields ==
    private final ShoppingListUserRepository shoppingListUserRepository;

    @Autowired
    public ShoppingListUserDetailsService(@Qualifier("fake") ShoppingListUserRepository shoppingListUserRepository) {
        this.shoppingListUserRepository = shoppingListUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.shoppingListUserRepository
                .selectApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " NOT FOUND!"));
    }

}
