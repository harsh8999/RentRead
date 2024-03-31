package com.harsh.RentRead.rent.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.book.repository.BookRepository;
import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.rent.dto.RentalDto;
import com.harsh.RentRead.rent.entity.Rental;
import com.harsh.RentRead.rent.repository.RentalRepository;
import com.harsh.RentRead.rent.services.RentalService;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.services.UserAuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RentalServiceImplementation implements RentalService {

    private final RentalRepository rentalRepository;

    private final UserAuthService userAuthService;

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private static final int RENTAL_LIMIT = 2;

    @Override
    public RentalDto rentBook(Long bookId) {
        User loggedInUser = userAuthService.getLoggedInUser();
        log.debug("Renting book with userId {} and bookId {}", loggedInUser.getId(), bookId);
        
        if(bookId == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        Book book = bookRepository.findByIdAndAvailabilityStatus(bookId, true)
            .orElseThrow(() -> new IllegalStateException("Book is not available for rent!"));

        // check if user has books than RENTAL_LIMIT books
        // we can get null meaning user has no books
        Optional<List<Rental>> userRentals = rentalRepository.findByUserIdAndActiveStatusTrue(loggedInUser);

        if(userRentals.isPresent() && userRentals.get().size() > RENTAL_LIMIT - 1) {
            log.warn("User with userId {} has reached the rental limit", loggedInUser.getId());
            throw new IllegalStateException("User cannot rent more than "+RENTAL_LIMIT+" books!");
        }

        
        Rental rental = new Rental();
        rental.setActiveStatus(true); // rent the book
        rental.setUserId(loggedInUser);
        rental.setBookId(book);

        Rental savedRental = rentalRepository.save(rental);

        // book is rented
        book.setAvailabilityStatus(false);
        bookRepository.save(book);

        log.info("Book rented successfully. RentalId: {}", savedRental.getId());

        return modelMapper.map(savedRental, RentalDto.class);
        
    }

    @Override
    public RentalDto returnBook(Long bookId) {
        User loggedInUser = userAuthService.getLoggedInUser();

        log.debug("Returning book with userId {} and bookId {}", loggedInUser.getId(), bookId);

        if(bookId == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "Book Id", Long.toString(bookId)));

        Rental rentedBook = rentalRepository.findByUserIdAndBookIdAndActiveStatusTrue(loggedInUser, book)
            .orElseThrow(() -> new IllegalStateException("No Book to return!"));
        
        
        // book is returned
        book.setAvailabilityStatus(true);
        bookRepository.save(book);

        rentedBook.setActiveStatus(false);

        Rental returnedBook = rentalRepository.save(rentedBook);

        log.info("Book returned successfully. RentalId: {}", returnedBook.getId());

        return modelMapper.map(returnedBook, RentalDto.class);

    }

    @Override
    public List<RentalDto> getAllRentals() {
        log.debug("Fetching all rentals");
        List<Rental> rentals = rentalRepository.findAll();
        log.info("Fetched {} rentals", rentals.size());
        return rentals.stream().map(rental -> modelMapper.map(rental, RentalDto.class)).collect(Collectors.toList());
    }

    @Override
    public RentalDto getRentalById(Long rentalId) {
        log.debug("Fetching rental with rentalId {}", rentalId);

        if(rentalId == null) {
            throw new IllegalArgumentException("Rental Id cannot be null!");
        }
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() -> new ResourceNotFoundException("Rental", "Rental Id", Long.toString(rentalId)));

        log.info("Fetched rental with rentalId {}", rentalId);
        return modelMapper.map(rental, RentalDto.class);
    }
    
}
