package com.harsh.RentRead.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.RentRead.user.controller.exchanges.UserUpdateDto;
import com.harsh.RentRead.user.dto.UserDto;
import com.harsh.RentRead.user.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling user-related endpoints.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    
    private static final String AUTHENTICATED_BASE_URL = "/users";

    private UserService userService;

    /**
     * Get all users.
     *
     * @return ResponseEntity with list of UserDto objects
     */
    @GetMapping(AUTHENTICATED_BASE_URL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Fetching all users from User Service");
        List<UserDto> users = userService.getAllUsers();
        log.info("Returning {} users", users.size());
        return ResponseEntity.ok().body(users);
    }
    
    /**
     * Get user by user ID.
     *
     * @param userId The ID of the user to retrieve
     * @return ResponseEntity with the UserDto object
     */
    @GetMapping(AUTHENTICATED_BASE_URL + "/{user_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable("user_id") Long userId) {
        log.info("Fetching user from User Service by user ID: {}", userId);
        UserDto user = userService.getUserByUserId(userId);
        log.info("User found: {}", user);
        return ResponseEntity.ok().body(user);
    }

    /**
     * Update user details.
     *
     * @param userId The ID of the user to update
     * @param userUpdateDto The DTO containing the updated user information
     * @return ResponseEntity with the updated UserDto object
     */
    @PutMapping(AUTHENTICATED_BASE_URL + "/{user_id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable("user_id") Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        log.info("Updating user with user ID: {}", userId);
        UserDto updatedUser = userService.updateUser(userId, userUpdateDto);
        log.info("User updated successfully: {}", updatedUser);
        return ResponseEntity.ok().body(updatedUser);
    }

    /**
     * Delete user by user ID.
     *
     * @param userId The ID of the user to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping(AUTHENTICATED_BASE_URL + "/{user_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long userId) {
        log.info("Deleting user by user ID: {}", userId);
        userService.deleteUser(userId);
        log.info("User deleted successfully");
        return ResponseEntity.noContent().build();
    }

    @PutMapping(AUTHENTICATED_BASE_URL + "/{user_id}/make_admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> makeUserAnAdmin(@PathVariable("user_id") Long userId) {
        log.info("Updating user role to ADMIN for user ID: {}", userId);
        UserDto updatedUser = userService.makeUserAdmin(userId);
        log.info("User updated successfully: {}", updatedUser);
        return ResponseEntity.ok().body(updatedUser);
    }
    
}
