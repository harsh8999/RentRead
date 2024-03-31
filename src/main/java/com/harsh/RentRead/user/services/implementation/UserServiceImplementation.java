package com.harsh.RentRead.user.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.user.controller.exchanges.UserUpdateDto;
import com.harsh.RentRead.user.dto.EmptyBodyDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.entity.enums.Role;
import com.harsh.RentRead.user.repository.UserRepository;
import com.harsh.RentRead.user.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the UserService interface.
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> {
            userDtos.add(modelMapper.map(user, UserDto.class));
        });

        log.info("Returning {} users", userDtos.size());
        return userDtos;
    }

    @Override
    public UserDto getUserByUserId(Long userId) {
        log.info("Fetching user by user ID: {}", userId);
        if(userId == null) {
            throw new IllegalArgumentException("User Id cannot be null!!!");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", Long.toString(userId)));
        log.info("User found: {}", user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Fetching user by email: {}", email);
        if(email == null) {
            throw new IllegalArgumentException("Email cannot be null!!!");
        }
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
        
        log.info("User found: {}", user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public EmptyBodyDto deleteUser(Long userId) {
        log.info("Deleting user by user ID: {}", userId);
        if(userId == null) {
            throw new IllegalArgumentException("User Id cannot be null!!!");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", Long.toString(userId)));
        userRepository.delete(user);
        log.info("User deleted successfully");
        return new EmptyBodyDto();
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateDto userUpdateDto) {
        log.info("Updating user with user ID: {}", userId);
        if(userId == null) {
            throw new IllegalArgumentException("User Id cannot be null!!!");
        }

        if(userUpdateDto == null) {
            throw new IllegalArgumentException("User update details cannot be null!!!");
        }
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", Long.toString(userId)));

        // update if details is present and not blank
        if(userUpdateDto.getFirstName() != null && userUpdateDto.getFirstName().length() != 0) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if(userUpdateDto.getEmail() != null && userUpdateDto.getEmail().length() != 0) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getLastName() != null && userUpdateDto.getLastName().length() != 0) {
            user.setLastName(userUpdateDto.getLastName());
        }
        if(userUpdateDto.getPassword() != null && userUpdateDto.getPassword().length() != 0) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        log.info("User updated successfully: {}", updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto makeUserAdmin(Long userId) {
        log.info("Making user with ID {} an admin", userId);
        if(userId == null) {
            throw new IllegalArgumentException("User Id cannot be null!!!");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId.toString()));
        user.addRole(Role.ADMIN);
        User updatedUser = userRepository.save(user);
        log.info("User with ID {} and email {} is now an admin", userId, user.getEmail());
        return modelMapper.map(updatedUser, UserDto.class);
    }
    
}
