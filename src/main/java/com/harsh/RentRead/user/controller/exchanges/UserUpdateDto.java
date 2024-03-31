package com.harsh.RentRead.user.controller.exchanges;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing the update information for a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDto {

    /**
     * The updated first name of the user.
     */
    @NotEmpty
    String firstName;

    /**
     * The updated last name of the user.
     */
    @NotEmpty
    String lastName;

    /**
     * The updated email address of the user.
     */
    @NotEmpty
    String email;

    /**
     * The updated password of the user.
     */
    @NotEmpty
    String password;
    
}

//@NotEmpty, an empty string will be considered invalid, but null will still be allowed.