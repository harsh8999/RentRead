package com.harsh.RentRead.rent.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.harsh.RentRead.rent.controllers.exchanges.ApiResponse;
import com.harsh.RentRead.rent.dto.RentalDto;
import com.harsh.RentRead.rent.services.RentalService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
public class RentalController {
    
    private RentalService rentalService;

    private static final String BASE_URL = "/rental";

    @PostMapping(BASE_URL + "/books/{book_id}/rent")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<RentalDto> rentBook(@PathVariable("book_id") Long bookId) {
        log.debug("Received request to rent book with bookId {}",bookId);
        RentalDto rentalDto = rentalService.rentBook(bookId);
        log.info("Book rented successfully for userId {} and bookId {}", rentalDto.getUser().getId(), bookId);
        return ResponseEntity.ok().body(rentalDto);
    }


    @PostMapping(BASE_URL + "/books/{book_id}/return")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> returnBook(@PathVariable("book_id") Long bookId) {
        log.debug("Received request to return book with bookId {}", bookId);
        RentalDto returnedBook = rentalService.returnBook(bookId);
        log.info("Book returned successfully for userId {} and bookId {}", returnedBook.getUser().getId(), bookId);

        return ResponseEntity.ok().body(new ApiResponse("Book returned successfully."));
    }

    @GetMapping(BASE_URL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RentalDto>> getAllRental() {
        log.debug("Received request to get all rentals");
        List<RentalDto> rentalDtos = rentalService.getAllRentals();
        log.info("Returning All Rental details");
        return ResponseEntity.ok().body(rentalDtos);
    }

    @GetMapping(BASE_URL + "/users/{user_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RentalDto>> getRental(@PathVariable("user_id") Long userId) {
        log.debug("Received request to get rental details of User Id {}", userId);
        List<RentalDto> rentalDtos = rentalService.getAllRentalsOfUser(userId);
        log.info("Returning Rental details of User Id {}", userId);
        return ResponseEntity.ok().body(rentalDtos);
    }

}
