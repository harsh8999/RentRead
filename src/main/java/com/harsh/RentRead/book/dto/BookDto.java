package com.harsh.RentRead.book.dto;

import com.harsh.RentRead.book.entity.enums.Genre;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) representing a book.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    /**
     * The unique identifier of the book.
     */
    Long id;
    
    /**
     * The title of the book.
     */
    String title;

    /**
     * The author of the book.
     */
    String author;
    
    /**
     * The genre of the book.
     */
    Genre genre;

    /**
     * The availability status of the book.
     */
    boolean availabilityStatus;
}
