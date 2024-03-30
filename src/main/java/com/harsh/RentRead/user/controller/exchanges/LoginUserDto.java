package com.harsh.RentRead.user.controller.exchanges;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing the information required for user login.
 * Used for exchanging user login credentials between the client and the server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginUserDto {
    
    /**
     * The email address of the user.
     * This field is required and cannot be null.
     */
    @NotNull
    String email;

    /**
     * The password of the user.
     * This field is required and cannot be null.
     */
    @NotNull
    String password;

}
