package com.harsh.RentRead.user.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.user.controller.exchanges.LoginUserDto;
import com.harsh.RentRead.user.controller.exchanges.SignUpUserDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.entity.enums.Role;
import com.harsh.RentRead.user.repository.UserRepository;
import com.harsh.RentRead.user.services.UserAuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the UserAuthService interface.
 * Provides methods for user authentication and sign-up.
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserAuthServiceImplementation implements UserAuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;

    @Override
    public UserDto signUp(SignUpUserDto requestUserDto) {
        if (requestUserDto == null || requestUserDto.getEmail() == null || requestUserDto.getPassword() == null) {
            log.error("Invalid sign up request: {}", requestUserDto);
            throw new IllegalArgumentException("Request cannot be null or missing required fields");
        }

        User user = new User();
        user.setFirstName(requestUserDto.getFirstName());
        user.setLastName(requestUserDto.getLastName());
        user.setEmail(requestUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
        
        if (requestUserDto.getRole() == null) {
            user.addRole(Role.USER);
        } else {
            if (requestUserDto.getRole().equalsIgnoreCase("ADMIN")) 
                user.addRole(Role.ADMIN);
            if (requestUserDto.getRole().equalsIgnoreCase("USER")) 
                user.addRole(Role.USER);
        }

        // save the user
        User savedUser = userRepository.save(user);
        
        // log
        log.info("User signed up successfully: {}", requestUserDto.getEmail());

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto authenticate(LoginUserDto loginUserDto) {
        // log
        log.info("Authenticating user with email: {}", loginUserDto.getEmail());
            
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
        log.info("Authentication successful for user with email: {}", loginUserDto.getEmail());
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "Email", loginUserDto.getEmail()));
        return modelMapper.map(user, UserDto.class);
    }
    
}
