package com.harsh.RentRead.user.services;

import java.util.List;

import com.harsh.RentRead.user.controller.exchanges.AddRequestRoleDto;
import com.harsh.RentRead.user.controller.exchanges.UserUpdateDto;
import com.harsh.RentRead.user.dto.EmptyBodyDto;
import com.harsh.RentRead.user.dto.UserDto;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Retrieve a list of all users.
     *
     * @return A list of all users.
     */
    List<UserDto> getAllUsers();

    /**
     * Retrieve a user by user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user DTO corresponding to the given user ID.
     */
    UserDto getUserByUserId(Long userId);

    /**
     * Retrieve a user by email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The user DTO corresponding to the given email address.
     */
    UserDto getUserByEmail(String email);

    /**
     * Update a user's information.
     *
     * @param userId The ID of the user to update.
     * @param userUpdateDto The DTO containing the updated user information.
     * @return The updated user DTO.
     */
    UserDto updateUser(Long userId, UserUpdateDto userUpdateDto);

    /**
     * Delete a user by user ID.
     *
     * @param userId The ID of the user to delete.
     * @return An empty body DTO indicating the success of the deletion operation.
     */
    EmptyBodyDto deleteUser(Long userId);

    

    /**
     * Adds a role to a user.
     *
     * @param userId The ID of the user to add the role to.
     * @param role   The role to add.
     * @return The user DTO with the added role.
     */
    UserDto addRole(Long userId, AddRequestRoleDto role);
    
}
