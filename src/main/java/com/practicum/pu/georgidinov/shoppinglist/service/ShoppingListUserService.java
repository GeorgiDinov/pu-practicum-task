package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.command.RegisteredUserCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.UserCommand;

public interface ShoppingListUserService {

    RegisteredUserCommand save(UserCommand userCommand);

    RegisteredUserCommand getRegisteredUserCommand();
}
