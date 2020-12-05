package com.practicum.pu.georgidinov.shoppinglist.auth;


import java.util.Optional;

public interface ShoppingListUserRepository {

    Optional<ShoppingListUserDetails>  selectApplicationUserByUsername(String username);

}
