package com.harsh.RentRead.user.services;

import com.harsh.RentRead.user.controller.exchanges.LoginUserDto;
import com.harsh.RentRead.user.controller.exchanges.SignUpUserDto;
import com.harsh.RentRead.user.dto.UserDto;

/**
 * Service interface for user authentication and registration operations.
 */
public interface UserAuthService {
    
    /**
     * Registers a new user based on the provided SignUpUserDto.
     * 
     * @param requestUserDto The SignUpUserDto containing user registration information.
     * @return The UserDto representing the registered user.
     */
    UserDto signUp(SignUpUserDto requestUserDto);

    /**
     * Authenticates a user based on the provided LoginUserDto.
     * 
     * @param loginUserDto The LoginUserDto containing user login credentials.
     * @return The UserDto representing the authenticated user.
     */
    UserDto authenticate(LoginUserDto loginUserDto);

}
