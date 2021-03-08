package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUserCredentials;
import com.practicum.pu.georgidinov.shoppinglist.model.RegisteredUserCommand;
import com.practicum.pu.georgidinov.shoppinglist.model.UserCredentials;
import com.practicum.pu.georgidinov.shoppinglist.model.UserRegistrationRequest;
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
import java.util.function.Consumer;

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
    public RegisteredUserCommand save(UserRegistrationRequest userRegistrationRequest) {
        //todo userCommand verification
        // only null fields verification
        this.registrationRequestVerifier.accept(userRegistrationRequest);

        String email = userRegistrationRequest.getUserCredentials().getEmail();

        if (isUsernameExists(email)) {
            log.info("{} exists!", email);
            throw new RuntimeException("Username " + email + " exists");//todo implement custom exception
        }

        log.info("ShoppingListUserServiceImpl save(), DTO Passed = {}", userRegistrationRequest);
        ShoppingListUser newUser = this.createUserFromUserCommand(userRegistrationRequest);
        ShoppingListUser savedUser = this.userRepository.save(newUser);
        log.info("ShoppingListUserServiceImpl save() savedUser data = {} ", savedUser.getUserCredentials());
        RegisteredUserCommand registeredUserCommand = RegisteredUserCommand.builder()
                .username(savedUser.getUserCredentials().getEmail())
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
    private ShoppingListUser createUserFromUserCommand(UserRegistrationRequest userRegistrationRequest) {
        UserCredentials credentials = userRegistrationRequest.getUserCredentials();

        ShoppingListUserCredentials userCredentials = ShoppingListUserCredentials.builder()
                .email(credentials.getEmail())
                .password(passwordEncoder.encode(credentials.getPassword()))
                .userRole(USER).build();

        ShoppingListUser user = ShoppingListUser.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .userCredentials(userCredentials).build();

        userCredentials.setUser(user);

        log.info("New UserCredentials Created -> {}", userCredentials);
        log.info("New User Created -> {}", user);
        return user;
    }

    private boolean isUsernameExists(String username) {
        Optional<ShoppingListUserCredentials> optionalCredentials =
                this.userCredentialsRepository.findByEmail(username);
        return optionalCredentials.isPresent();
    }

    private final Consumer<UserRegistrationRequest> registrationRequestVerifier = request -> {
        if (request == null) {
            throw new RuntimeException("Request is Null");
        }
        if (request.getFirstName() == null) {
            throw new RuntimeException("Request First Name is Null");
        }
        if (request.getLastName() == null) {
            throw new RuntimeException("Request Last Name is Null");
        }
        if (request.getUserCredentials() == null) {
            throw new RuntimeException("Request Credentials are Null");
        }
        if (request.getUserCredentials().getEmail() == null) {
            throw new RuntimeException("Request Credentials Email is Null");
        }
        if (request.getUserCredentials().getPassword() == null) {
            throw new RuntimeException("Request Credentials Password is Null");
        }
    };

}