package com.practicum.pu.georgidinov.shoppinglist.restcontroller;


import com.practicum.pu.georgidinov.shoppinglist.command.RegisteredUserCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.UserCommand;
import com.practicum.pu.georgidinov.shoppinglist.service.ShoppingListUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RegisteredUserCommand register(@RequestBody UserCommand userCommand) {
        log.info("ShoppingListUserController register() DTO Passed = {}", userCommand);
        RegisteredUserCommand command = this.userService.save(userCommand);
        log.info("ShoppingListUserController register() Command Returned = {}", command);
        return command;
    }

    @GetMapping("/userinfo")
    public RegisteredUserCommand getRegisteredUserCommand() {
        log.info("ShoppingListUserController getRegisteredUserCommand()");
        RegisteredUserCommand command = this.userService.getRegisteredUserCommand();
        log.info("ShoppingListUserController getRegisteredUserCommand() Command Returned = {}", command);
        return command;
    }
}
