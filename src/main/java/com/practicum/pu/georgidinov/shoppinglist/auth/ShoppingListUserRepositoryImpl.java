package com.practicum.pu.georgidinov.shoppinglist.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.ADMIN;
import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.USER;

@Repository("fake")
public class ShoppingListUserRepositoryImpl implements ShoppingListUserRepository {

    //== fields ==
    private final PasswordEncoder passwordEncoder;

    //== constructors ==
    @Autowired
    public ShoppingListUserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ShoppingListUserDetails> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(shoppingListUserDetails -> username.equals(shoppingListUserDetails.getUsername()))
                .findFirst();
    }

    private List<ShoppingListUserDetails> getApplicationUsers() {
        return Lists.newArrayList(
                new ShoppingListUserDetails(
                        "user",
                        passwordEncoder.encode("password"),
                        USER.getGrantedAuthorities()
                ),
                new ShoppingListUserDetails(
                        "admin",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities()
                )
        );
    }

}