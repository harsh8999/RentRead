package com.harsh.RentRead.user.dto;

import com.harsh.RentRead.user.entity.enums.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    /**
     * The unique identifier for the user.
     */
    Long id;

    /**
     * The first name of the user.
     */
    String firstName;

    /**
     * The last name of the user.
     */
    String lastName;

    /**
     * The email address of the user.
     */
    String email;

    /**
     * The role of the user (e.g., USER or ADMIN).
     */
    Role role;
    
}
