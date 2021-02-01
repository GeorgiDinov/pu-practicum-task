package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.command.RegisteredUserCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.UserCommand;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUserCredentials;
import com.practicum.pu.georgidinov.shoppinglist.repository.ShoppingListUserCredentialsRepository;
import com.practicum.pu.georgidinov.shoppinglist.repository.ShoppingListUserRepository;
import com.practicum.pu.georgidinov.shoppinglist.security.auth.ShoppingListUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.USER;

@Slf4j
@Service
public class ShoppingListUserServiceImpl implements ShoppingListUserService {

    //== fields ==
    private final ShoppingListUserRepository userRepository;
    private final ShoppingListUserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;

    //== constructors ==
    @Autowired
    public ShoppingListUserServiceImpl(ShoppingListUserRepository userRepository,
                                       ShoppingListUserCredentialsRepository userCredentialsRepository,
                                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //== public methods ==
    @Override
    public RegisteredUserCommand save(UserCommand userCommand) {
        //todo userCommand verification
        if (isUsernameExists(userCommand.getUsername())) {
            log.info("{} exists!", userCommand.getUsername());
            throw new RuntimeException("Username " + userCommand.getUsername() + " exists");//todo implement custom exception
        }

        log.info("ShoppingListUserServiceImpl save(), DTO Passed = {}", userCommand);
        ShoppingListUser newUser = this.createUserFromUserCommand(userCommand);
        ShoppingListUser savedUser = this.userRepository.save(newUser);
        log.info("ShoppingListUserServiceImpl save() savedUser data = {} ", savedUser.getUserCredentials());
        RegisteredUserCommand registeredUserCommand = RegisteredUserCommand.builder()
                .username(savedUser.getUserCredentials().getUsername())
                .id(savedUser.getId()).build();
        log.info("ShoppingListUserServiceImpl save() Command Returned = {}", registeredUserCommand);
        return registeredUserCommand;
    }

    @Override
    public RegisteredUserCommand getRegisteredUserCommand() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ShoppingListUserDetails loggedInUserDetails = (ShoppingListUserDetails) authentication.getPrincipal();
        RegisteredUserCommand registeredUserCommand = RegisteredUserCommand.builder()
                .username(loggedInUserDetails.getUsername())
                .id(loggedInUserDetails.getUserId()).build();
        log.info("ShoppingListUserServiceImpl getRegisteredUserCommand() Command Returned = {}", registeredUserCommand);
        return registeredUserCommand;
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
                .userCredentials(userCredentials).build();

        userCredentials.setUser(user);

        log.info("New UserCredentials Created -> {}", userCredentials);
        log.info("New User Created -> {}", user);
        return user;
    }

    private boolean isUsernameExists(String username) {
        Optional<ShoppingListUserCredentials> optionalCredentials =
                this.userCredentialsRepository.findByUsername(username);
        return optionalCredentials.isPresent();
    }

}