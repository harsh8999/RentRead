package com.harsh.RentRead.rent.services;

import java.util.List;

import com.harsh.RentRead.rent.dto.RentalDto;

/**
 * Service interface for managing rentals.
 */
public interface RentalService {

    /**
     * Rent a book.
     *
     * @param bookId The ID of the book to rent.
     * @return The DTO representing the rental.
     */
    RentalDto rentBook(Long bookId);

    /**
     * Return a rented book.
     *
     * @param bookId The ID of the book to return.
     * @return The DTO representing the returned rental.
     */
    RentalDto returnBook(Long bookId);

    // methods for admin
    /**
     * Get all rentals (for admin use).
     *
     * @return A list of DTOs representing all rentals.
     */
    List<RentalDto> getAllRentals();

    /**
     * Get a rental by its ID (for admin use).
     *
     * @param rentalId The ID of the rental to retrieve.
     * @return The DTO representing the rental.
     */
    RentalDto getRentalById(Long rentalId);
    
}
