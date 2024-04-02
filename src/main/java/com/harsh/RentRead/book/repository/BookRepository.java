package com.harsh.RentRead.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.RentRead.book.entity.Book;

/**
 * Repository interface for managing books.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Retrieves a book by its ID and availability status.
     *
     * @param bookId             The ID of the book to retrieve.
     * @param availabilityStatus The availability status of the book.
     * @return An Optional containing the book, or empty if not found.
     */
    Optional<Book> findByIdAndAvailabilityStatus(Long bookId, boolean availabilityStatus);
    
}
