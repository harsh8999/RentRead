package com.harsh.RentRead.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.RentRead.user.controller.exchanges.LoginUserDto;
import com.harsh.RentRead.user.controller.exchanges.SignUpUserDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.services.UserAuthService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling user authentication operations.
 */
@Slf4j
@RestController
public class UserAuthController {

    private static final String BASE_URL = "/api/v1/auth";
    
    private UserAuthService userAuthService;

    /**
     * Constructs a new UserAuthController with the specified UserAuthService.
     * 
     * @param userAuthService The UserAuthService to be used for user authentication.
     */
    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    /**
     * Handles the root directory endpoint and returns a welcome message.
     * 
     * @return ResponseEntity containing the welcome message.
     */
    @GetMapping("/")
    public ResponseEntity<String> getRootDirectory() {
        String message = "Welcome to the RentRead application!";

        // Log the message
        log.info("Root directory accessed. Response: {}", message);

        return ResponseEntity.ok().body(message);
    }
    
    /**
     * Handles the user sign-up endpoint and returns the newly signed-up user.
     * 
     * @param signUpUserDto The SignUpUserDto containing user sign-up details.
     * @return ResponseEntity containing the newly signed-up user DTO.
     */
    @PostMapping(BASE_URL + "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpUserDto signUpUserDto) {
        // Log the message
        log.info("Received sign-up request for email: {}", signUpUserDto.getEmail());

        UserDto userDto = userAuthService.signUp(signUpUserDto);

        // Log the message
        log.info("User signed up successfully with email: {}", signUpUserDto.getEmail());

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    
    /**
     * Handles the user login endpoint and returns the authenticated user.
     * 
     * @param loginUserDto The LoginUserDto containing user login credentials.
     * @return ResponseEntity containing the authenticated user DTO or HTTP UNAUTHORIZED status if login fails.
     */
    @PostMapping(BASE_URL + "/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginUserDto loginUserDto) {
        
        log.info("Attempting login for user with email: {}", loginUserDto.getEmail());

        UserDto userDto = userAuthService.authenticate(loginUserDto);

        if (userDto != null) {
            // log
            log.info("User {} with email {} logged in successfully", userDto.getFirstName() + userDto.getLastName(), userDto.getEmail());
            return ResponseEntity.ok(userDto);
        } else {
            // log
            log.warn("Login failed for user with email: {}", loginUserDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
}
