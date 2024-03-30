package com.harsh.RentRead.user.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.RentRead.user.controller.exchanges.LoginUserDto;
import com.harsh.RentRead.user.controller.exchanges.SignUpUserDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.entity.enums.Role;
import com.harsh.RentRead.user.repository.UserRepository;
import com.harsh.RentRead.user.services.UserAuthService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAuthServiceImplementation implements UserAuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;

    public UserAuthServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder,
            ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto signUp(SignUpUserDto requestUserDto) {
        if (requestUserDto == null || requestUserDto.getEmail() == null || requestUserDto.getPassword() == null) {
            log.error("Invalid sign up request: {}", requestUserDto);
            throw new IllegalArgumentException("Request cannot be null or missing required fields");
        }

        User user = new User().builder()
            .firstName(requestUserDto.getFirstName())
            .lastName(requestUserDto.getLastName())
            .email(requestUserDto.getEmail())
            .password(passwordEncoder.encode(requestUserDto.getPassword()))
            .build();
        
        if (requestUserDto.getRole() == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(Role.valueOf(requestUserDto.getRole().toUpperCase()));
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
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not Found!!!"));
        return modelMapper.map(user, UserDto.class);
    }
    
}
