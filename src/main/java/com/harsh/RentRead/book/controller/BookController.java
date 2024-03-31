package com.harsh.RentRead.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.RentRead.book.controller.exchanges.AddBookExchangeDto;
import com.harsh.RentRead.book.controller.exchanges.UpdateBookExchangeDto;
import com.harsh.RentRead.book.dto.BookDto;
import com.harsh.RentRead.book.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for managing books.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BookController {
    
    private final BookService bookService;

    private static final String BASE_URL = "/books";

    /**
     * Endpoint for adding a book.
     *
     * @param addBookExchangeDto The DTO containing the details of the book to add.
     * @return ResponseEntity representing the status of the addition operation along with the added book DTO.
     */
    @PostMapping(BASE_URL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody AddBookExchangeDto addBookExchangeDto) {
        log.info("Received add book request (POST) for book title: {}", addBookExchangeDto.getTitle());
        BookDto bookDto = bookService.addBook(addBookExchangeDto);
        log.info("Book added successfully with Book Id {}", bookDto.getId());
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    /**
     * Endpoint for adding multiple books.
     *
     * @param addBookExchangeDtos The list of DTOs containing the details of the books to add.
     * @return ResponseEntity representing the status of the addition operation along with the list of added book DTOs.
     */
    @PostMapping(BASE_URL + "/addAllBooks")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BookDto>> addAllBooks(@Valid @RequestBody List<AddBookExchangeDto> addBookExchangeDtos) {
        log.info("Received add book request (POST) for books");
        List<BookDto> bookDtos = bookService.addAllBooks(addBookExchangeDtos);
        log.info("All Books added successfully");
        return new ResponseEntity<>(bookDtos, HttpStatus.CREATED);
    }

    // every user can get all the books
    /**
     * Endpoint for retrieving all books.
     *
     * @return ResponseEntity representing the status of the retrieval operation along with the list of book DTOs.
     */
    @GetMapping(BASE_URL)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("Fetching all books");
        List<BookDto> bookDtos = bookService.getBooks();
        log.info("Returning {} books", bookDtos.size());
        return ResponseEntity.ok().body(bookDtos);
    }

    // every user can get the book
    /**
     * Endpoint for retrieving a book by its ID.
     *
     * @param bookId The ID of the book to retrieve.
     * @return ResponseEntity representing the status of the retrieval operation along with the book DTO.
     */
    @GetMapping(BASE_URL + "/{book_id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<BookDto> getBookById(@PathVariable("book_id") Long bookId) {
        log.info("Fetching book with Book Id {}", bookId);
        BookDto bookDto = bookService.getBookById(bookId);
        log.info("Returning book with Book Id {}", bookDto.getId());
        return ResponseEntity.ok().body(bookDto);
    }

    /**
     * Endpoint for updating a book by its ID.
     *
     * @param bookId The ID of the book to update.
     * @param updateBookExchangeDto The DTO containing the updated details of the book.
     * @return ResponseEntity representing the status of the update operation along with the updated book DTO.
     */
    @PutMapping(BASE_URL + "/{book_id}" + "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookDto> updateBookById(@PathVariable("book_id") Long bookId, @RequestBody UpdateBookExchangeDto updateBookExchangeDto) {
        log.info("Updating book with Book Id {}", bookId);
        BookDto updatedBookDto = bookService.updateBook(bookId, updateBookExchangeDto);
        log.info("Returning book with Book Id {}", updatedBookDto.getId());
        return ResponseEntity.ok().body(updatedBookDto);
    }

    /**
     * Endpoint for deleting a book by its ID.
     *
     * @param bookId The ID of the book to delete.
     * @return ResponseEntity representing the status of the deletion operation.
     */
    @DeleteMapping(BASE_URL + "/{book_id}" + "/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateBookById(@PathVariable("book_id") Long bookId) {
        log.info("Delete request for book with Book Id {}", bookId);
        bookService.deleteBookById(bookId);
        log.info("Book with Book Id {} deleted successfully", bookId);
        return ResponseEntity.noContent().build();
    }

}
