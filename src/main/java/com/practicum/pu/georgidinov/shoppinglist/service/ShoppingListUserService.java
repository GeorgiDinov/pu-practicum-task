package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.command.UserCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.WelcomeInfoCommand;
import org.springframework.security.core.Authentication;

public interface ShoppingListUserService {

    WelcomeInfoCommand save(UserCommand userCommand);

    WelcomeInfoCommand welcomeInfo(Authentication authentication);
}
