package com.harsh.RentRead.exception.exchangeDtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * DTO (Data Transfer Object) representing an API response for an exception.
 * Contains the error message.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionApiResponse {
    
    /**
     * The error message describing the exception.
     */
    String message;
}