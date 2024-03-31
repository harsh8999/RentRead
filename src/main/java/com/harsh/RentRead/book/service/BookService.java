package com.harsh.RentRead.book.service;

import java.util.List;

import com.harsh.RentRead.book.controller.exchanges.AddBookExchangeDto;
import com.harsh.RentRead.book.controller.exchanges.UpdateBookExchangeDto;
import com.harsh.RentRead.book.dto.BookDto;
import com.harsh.RentRead.user.dto.EmptyBodyDto;

/**
 * Service interface for managing books.
 */
public interface BookService {

    /**
     * Adds a new book to the database.
     * 
     * @param bookDto The DTO containing information about the book to be added.
     * @return The DTO representing the newly added book.
     */
    BookDto addBook(AddBookExchangeDto bookDto);

    /**
     * Adds multiple books to the database.
     * 
     * @param bookDtos A list of DTOs containing information about the books to be added.
     * @return A list of DTOs representing the newly added books.
     */
    List<BookDto> addAllBooks(List<AddBookExchangeDto> bookDtos);

    /**
     * Retrieves all books from the database.
     * 
     * @return A list of DTOs representing all books.
     */
    List<BookDto> getBooks();

    /**
     * Retrieves a book by its ID from the database.
     * 
     * @param bookId The ID of the book to retrieve.
     * @return The DTO representing the retrieved book.
     */
    BookDto getBookById(Long bookId);

    /**
     * Updates a book in the database.
     * 
     * @param bookId The ID of the book to update.
     * @param bookDto The DTO containing the updated information of the book.
     * @return The DTO representing the updated book.
     */
    BookDto updateBook(Long bookId, UpdateBookExchangeDto bookDto);

    /**
     * Deletes a book from the database by its ID.
     * 
     * @param bookId The ID of the book to delete.
     * @return An EmptyBodyDto representing the success of the deletion operation.
     */
    EmptyBodyDto deleteBookById(Long bookId);
    
}
