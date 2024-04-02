package com.harsh.RentRead.user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.user.controller.exchanges.LoginUserDto;
import com.harsh.RentRead.user.controller.exchanges.SignUpUserDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.entity.enums.Role;
import com.harsh.RentRead.user.repository.UserRepository;
import com.harsh.RentRead.user.services.implementation.UserAuthServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserAuthServiceImplementationTest {
    
    @MockBean
    private UserRepository userRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserAuthServiceImplementation authService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        authService = new UserAuthServiceImplementation(userRepository, passwordEncoder, modelMapper, authenticationManager);
    }

    @Test
    public void signUpTest() {
        log.info("Starting signUpTest");
        SignUpUserDto signUpUserDto = new SignUpUserDto("Harsh", "Nayak", "harsh@example.com", "123", Role.USER);
        User user = new User();
        user.setEmail(signUpUserDto.getEmail());
        user.setPassword("123");

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(signUpUserDto.getPassword())).thenReturn("123");

        UserDto result = authService.signUp(signUpUserDto);

        // Assert
        assertNotNull(result);
        assertEquals(signUpUserDto.getEmail(), result.getEmail());
        log.info("signUpTest successfully completed");
    }

    @Test
    public void signUpThrowsIllegalArgumentException() {
        log.info("Starting signUpThrowsIllegalArgumentException");
        // Assert
        assertThrows(IllegalArgumentException.class, () -> authService.signUp(null));
        log.info("signUpThrowsIllegalArgumentException successfully completed");
    }

    @Test
    public void authenticateValidCredentialsTest() {
        log.info("Starting authenticateValidCredentialsTest");

        String email = "harsh@gmail.com";
        String password = "pass";
        LoginUserDto loginUserDto = new LoginUserDto(email, password);

        User user = new User();
        user.setEmail(loginUserDto.getEmail());
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // security config
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);

        UserDto result = authService.authenticate(loginUserDto);

        assertNotNull(result);
        assertEquals(loginUserDto.getEmail(), result.getEmail());
        
        log.info("authenticateValidCredentialsTest successfully completed");
    }

    @Test
    public void authenticateInvalidCredentialsThrowsResourceNotFoundException() {
        log.info("Starting authenticateInvalidCredentialsThrowsResourceNotFoundException");
        LoginUserDto loginUserDto = new LoginUserDto("harsh@example.com", "pas12");
        when(userRepository.findByEmail(loginUserDto.getEmail())).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> authService.authenticate(loginUserDto));
        log.info("authenticateInvalidCredentialsThrowsResourceNotFoundException successfully completed");
    }

}
