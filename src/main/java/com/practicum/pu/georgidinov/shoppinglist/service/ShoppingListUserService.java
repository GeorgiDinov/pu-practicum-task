package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.model.RegisteredUserCommand;
import com.practicum.pu.georgidinov.shoppinglist.model.UserRegistrationRequest;

public interface ShoppingListUserService {

    RegisteredUserCommand save(UserRegistrationRequest userRegistrationRequest);

    RegisteredUserCommand getRegisteredUserCommand();
}
