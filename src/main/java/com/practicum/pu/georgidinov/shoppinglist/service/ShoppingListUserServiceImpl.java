package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.auth.ShoppingListUserDetails;
import com.practicum.pu.georgidinov.shoppinglist.command.UserCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.WelcomeInfoCommand;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUserCredentials;
import com.practicum.pu.georgidinov.shoppinglist.repository.ShoppingListUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.USER;

@Slf4j
@Service
public class ShoppingListUserServiceImpl implements ShoppingListUserService {

    //== fields ==
    private final ShoppingListUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //== constructors ==
    @Autowired
    public ShoppingListUserServiceImpl(ShoppingListUserRepository userRepository,
                                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //== public methods ==
    @Override
    public WelcomeInfoCommand save(UserCommand userCommand) {
        log.info("inside shopping list user service save, DTO = {}", userCommand);
        ShoppingListUser newUser = this.createUserFromUserCommand(userCommand);
        ShoppingListUser savedUser = this.userRepository.save(newUser);
        WelcomeInfoCommand welcomeInfoCommand = WelcomeInfoCommand.builder()
                .username(savedUser.getCredentials().getUsername())
                .id(savedUser.getId()).build();
        log.info("user service save() welcomeInfoCommand = {}", welcomeInfoCommand);
        return welcomeInfoCommand;
    }

    @Override
    public WelcomeInfoCommand welcomeInfo(Authentication authentication) {
        ShoppingListUserDetails loggedInUserDetails = (ShoppingListUserDetails) authentication.getPrincipal();
        WelcomeInfoCommand welcomeInfoCommand = WelcomeInfoCommand.builder()
                .username(loggedInUserDetails.getUsername())
                .id(loggedInUserDetails.getUserId()).build();
        log.info("user service welcomeInfo welcomeInfoCommand = {}", welcomeInfoCommand);
        return welcomeInfoCommand;
    }

    //== private methods ==
    private ShoppingListUser createUserFromUserCommand(UserCommand userCommand) {

        ShoppingListUserCredentials userCredentials = ShoppingListUserCredentials.builder()
                .username(userCommand.getUsername())
                .password(passwordEncoder.encode(userCommand.getPassword()))
                .userRole(USER).build();

        ShoppingListUser user = ShoppingListUser.builder()
                .firstName(userCommand.getFirstName())
                .lastName(userCommand.getLastName())
                .credentials(userCredentials).build();

        userCredentials.setUser(user);
        return user;
    }

}