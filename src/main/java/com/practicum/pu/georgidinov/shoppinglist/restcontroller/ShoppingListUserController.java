package com.practicum.pu.georgidinov.shoppinglist.restcontroller;


import com.practicum.pu.georgidinov.shoppinglist.model.RegisteredUserCommand;
import com.practicum.pu.georgidinov.shoppinglist.model.UserRegistrationRequest;
import com.practicum.pu.georgidinov.shoppinglist.service.ShoppingListUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredUserCommand register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        log.info("ShoppingListUserController register() DTO Passed = {}", userRegistrationRequest);
        RegisteredUserCommand command = this.userService.save(userRegistrationRequest);
        log.info("ShoppingListUserController register() Command Returned = {}", command);
        return command;
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }

}
