package com.harsh.RentRead.user.controller.exchanges;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing the information required for user sign-up.
 * Used for exchanging user data between the client and the server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpUserDto {

    /**
     * The first name of the user.
     */
    @NotNull
    String firstName;

    /**
     * The last name of the user.
     */
    String lastName;

    /**
     * The email address of the user.
     */
    @NotNull
    String email;

     /**
     * The password of the user.
     */
    @NotNull
    String password;

    /**
     * The role of the user.
     */
    String role;
}
