package com.harsh.RentRead.rent.services;

import java.util.List;

import com.harsh.RentRead.rent.dto.RentalDto;

public interface RentalService {
    RentalDto rentBook(Long bookId);
    RentalDto returnBook(Long bookId);

    // methods for admin
    List<RentalDto> getAllRentals();
    RentalDto getRentalById(Long rentalId);
    List<RentalDto> getAllRentalsOfUser(Long userId);
    
}
