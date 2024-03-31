package com.harsh.RentRead.rent.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.harsh.RentRead.rent.dto.RentalDto;
import com.harsh.RentRead.rent.services.RentalService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping(BASE_URL + "/users/{user_id}/books/{book_id}/rent")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<RentalDto> rentBook(@PathVariable("user_id") Long userId, @PathVariable("book_id") Long bookId) {
        log.debug("Received request to rent book with userId {} and bookId {}", userId, bookId);
        RentalDto rentalDto = rentalService.rentBook(userId, bookId);
        log.info("Book rented successfully for userId {} and bookId {}", userId, bookId);
        return ResponseEntity.ok().body(rentalDto);
    }


    @PostMapping(BASE_URL + "/users/{user_id}/books/{book_id}/return")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<RentalDto> returnBook(@PathVariable("user_id") Long userId, @PathVariable("book_id") Long bookId) {
        log.debug("Received request to return book with userId {} and bookId {}", userId, bookId);
        RentalDto rentalDto = rentalService.returnBook(userId, bookId);
        log.info("Book returned successfully for userId {} and bookId {}", userId, bookId);
        return ResponseEntity.ok().body(rentalDto);
    }
    



}
