package com.harsh.RentRead.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.RentRead.book.entity.Book;

/**
 * Repository interface for managing books.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
