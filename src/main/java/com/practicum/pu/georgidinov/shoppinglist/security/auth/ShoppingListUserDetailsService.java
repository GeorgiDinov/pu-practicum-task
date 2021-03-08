package com.practicum.pu.georgidinov.shoppinglist.security.auth;

import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUserCredentials;
import com.practicum.pu.georgidinov.shoppinglist.repository.ShoppingListUserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingListUserDetailsService implements UserDetailsService {

    private final ShoppingListUserCredentialsRepository shoppingListUserCredentialsRepository;

    @Autowired
    public ShoppingListUserDetailsService(ShoppingListUserCredentialsRepository shoppingListUserCredentialsRepository) {
        this.shoppingListUserCredentialsRepository = shoppingListUserCredentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ShoppingListUserCredentials> optionalShoppingListUserCredentials =
                this.shoppingListUserCredentialsRepository.findByEmail(username);

        ShoppingListUserCredentials userCredentials =
                optionalShoppingListUserCredentials
                        .orElseThrow(() -> new UsernameNotFoundException(username + " NOT FOUND"));

        return new ShoppingListUserDetails(userCredentials);
    }

}