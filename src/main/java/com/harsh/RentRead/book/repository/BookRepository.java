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
     * Retrieves an available book by its ID.
     *
     * @param bookId The ID of the book to retrieve.
     * @return An Optional containing the book if available, empty otherwise.
     */    
    Optional<Book> findByIdAndAvailabilityStatusTrue(Long bookId);
    
}
