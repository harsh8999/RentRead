package com.harsh.RentRead.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.rent.entity.Rental;
import com.harsh.RentRead.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing rentals.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    /**
     * Retrieves active rentals for a specific user.
     *
     * @param user The user to retrieve rentals for.
     * @return Optional containing a list of active rentals for the user.
     */
    Optional<List<Rental>> findByUserIdAndActiveStatusTrue(User user); 

    /**
     * Retrieves all rentals for a specific user.
     *
     * @param user The user to retrieve rentals for.
     * @return Optional containing a list of all rentals for the user.
     */
    Optional<List<Rental>> findByUserId(User user);

    /**
     * Retrieves an active rental for a specific user and book.
     *
     * @param user The user to retrieve the rental for.
     * @param book The book to retrieve the rental for.
     * @return Optional containing the active rental for the user and book.
     */
    Optional<Rental> findByUserIdAndBookIdAndActiveStatusTrue(User user, Book book); 
}
