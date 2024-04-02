package com.harsh.RentRead.book.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harsh.RentRead.book.controller.exchanges.AddBookExchangeDto;
import com.harsh.RentRead.book.controller.exchanges.UpdateBookExchangeDto;
import com.harsh.RentRead.book.dto.BookDto;
import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.book.repository.BookRepository;
import com.harsh.RentRead.book.service.BookService;
import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.user.dto.EmptyBodyDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the BookService interface.
 */
@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImplementaion implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookDto addBook(AddBookExchangeDto bookDto) {
        if (bookDto == null) {
            log.error("Invalid request: Book DTO is null.");
            throw new IllegalArgumentException("Request cannot be null or missing required fields");    
        }

        log.info("Adding book with title: {}, author: {}, genre: {}", bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setAvailabilityStatus(true);
        book.setGenre(bookDto.getGenre());

        Book savedBook = bookRepository.save(book);

        log.info("Book added successfully with ID: {}", savedBook.getId());

        return modelMapper.map(savedBook, BookDto.class);
    }

    @Override
    public List<BookDto> addAllBooks(List<AddBookExchangeDto> bookDtos) {
        log.info("Adding multiple books");

        if(bookDtos == null) {
            log.error("Invalid request: List of Book DTO is null.");
            throw new IllegalArgumentException("Request cannot be null or missing required fields");    
        }
        
        List<Book> booksToAdd = new ArrayList<>();
        bookDtos.forEach(bookDto -> {
            Book book = new Book();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setGenre(bookDto.getGenre());  
            book.setAvailabilityStatus(true);          
            booksToAdd.add(book);
        });

        log.info("Saving multiple books to the database");
        List<Book> savedBooks = bookRepository.saveAll(booksToAdd);

        log.info("Mapping saved books to DTOs");
        List<BookDto> savedBooksDto = new ArrayList<>();
        savedBooks.forEach(book -> savedBooksDto.add(modelMapper.map(book, BookDto.class)));

        log.info("Books added successfully");
        return savedBooksDto;
    }

    @Override
    public List<BookDto> getBooks() {
        log.info("Fetching all books");
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        books.forEach(book -> 
            bookDtos.add(modelMapper.map(book, BookDto.class)));
        log.info("Returning {} books", bookDtos.size());
        return bookDtos;
    }
    

    @Override
    public BookDto getBookById(Long bookId) {
        if(bookId == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Book Id", Long.toString(bookId)));
        log.info("Returning book Id : {}", bookId);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto updateBook(Long bookId, UpdateBookExchangeDto bookDto) {
        log.info("Updating Book with Book ID: {}", bookId);
        if(bookId == null) {
            throw new IllegalArgumentException("Book Id cannot be null!!!");
        }

        if(bookDto == null) {
            throw new IllegalArgumentException("Book update details cannot be null!!!");
        }
        
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "Book ID", Long.toString(bookId)));

        // update if details is present and not blank
        if(bookDto.getAuthor() != null && !bookDto.getAuthor().isBlank()) {
            book.setAuthor(bookDto.getAuthor());
        }

        if(bookDto.getGenre() != null) {
            book.setGenre(bookDto.getGenre());
        }

        if(bookDto.getTitle() != null && !bookDto.getTitle().isBlank()) {
            book.setTitle(bookDto.getTitle());
        }

        Book updatedbook = bookRepository.save(book);
        log.info("Book updated successfully: {}", updatedbook);
        return modelMapper.map(updatedbook, BookDto.class);
    }

    @Override
    public EmptyBodyDto deleteBookById(Long bookId) {
        log.info("Deleting Book by book ID: {}", bookId);
        if(bookId == null) {
            throw new IllegalArgumentException("Book Id cannot be null!!!");
        }
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "Book ID", Long.toString(bookId)));
        bookRepository.delete(book);
        log.info("Book deleted successfully");
        return new EmptyBodyDto();
    }
    
}
