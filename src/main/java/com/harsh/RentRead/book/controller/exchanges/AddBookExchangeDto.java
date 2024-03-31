package com.harsh.RentRead.book.controller.exchanges;

import com.harsh.RentRead.book.entity.enums.Genre;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * DTO class for adding a book.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookExchangeDto {
    
    /**
     * The title of the book. Cannot be blank.
     */
    @NotBlank
    String title;

    /**
     * The author of the book. Cannot be blank.
     */
    @NotBlank
    String author;
    
    /**
     * The genre of the book.
     */
    Genre genre;

}
