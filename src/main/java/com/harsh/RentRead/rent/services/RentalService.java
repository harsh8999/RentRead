package com.harsh.RentRead.rent.services;

import java.util.List;

import com.harsh.RentRead.rent.dto.RentalDto;

public interface RentalService {
    RentalDto rentBook(Long userId, Long bookId);
    RentalDto returnBook(Long userId, Long bookId);

    // methods for admin
    List<RentalDto> getAllRentals();
    RentalDto getRentalById(Long rentalId);
    List<RentalDto> getAllRentalsOfUser(Long userId);
    
}
