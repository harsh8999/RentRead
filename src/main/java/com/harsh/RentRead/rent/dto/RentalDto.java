package com.harsh.RentRead.rent.dto;

import java.util.Date;

import com.harsh.RentRead.book.dto.BookDto;
import com.harsh.RentRead.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Data transfer object for representing a rental.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentalDto {

    /**
     * The ID of the rental.
     */
    Long id;

    /**
     * The user who rented the book.
     */
    UserDto user;

    /**
     * The book that was rented.
     */
    BookDto book;

    /**
     * The date when the book was rented.
     */
    Date rentalDate;

    /**
     * The status indicating if the rental is active.
     */
    boolean activeStatus;

}
