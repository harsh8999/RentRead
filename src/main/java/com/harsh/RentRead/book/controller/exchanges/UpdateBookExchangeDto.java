package com.harsh.RentRead.book.controller.exchanges;

import com.harsh.RentRead.book.entity.enums.Genre;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * DTO class for updating book details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateBookExchangeDto {
    
    /**
     * The updated title of the book.
     */
    String title;

    /**
     * The updated author of the book.
     */
    String author;

    /**
     * The updated genre of the book.
     */
    Genre genre;
}
