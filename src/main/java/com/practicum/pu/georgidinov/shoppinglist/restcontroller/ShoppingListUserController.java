package com.practicum.pu.georgidinov.shoppinglist.restcontroller;


import com.practicum.pu.georgidinov.shoppinglist.command.UserCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.WelcomeInfoCommand;
import com.practicum.pu.georgidinov.shoppinglist.service.ShoppingListUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ShoppingListUserController {

    //==fields ==
    private final ShoppingListUserService userService;

    //== constructors ==
    @Autowired
    public ShoppingListUserController(ShoppingListUserService userService) {
        this.userService = userService;
    }

    //== public methods ==
    @PostMapping("/register")
    public WelcomeInfoCommand register(@RequestBody UserCommand userCommand) {
        log.info("ShoppingListUserController register method DTO = {}", userCommand);
        return this.userService.save(userCommand);
    }

    @GetMapping("/userinfo")
    public WelcomeInfoCommand welcomeInfo(Authentication authentication) {
        return this.userService.welcomeInfo(authentication);
    }
}
