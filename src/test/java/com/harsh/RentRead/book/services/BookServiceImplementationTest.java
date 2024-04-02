package com.harsh.RentRead.book.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.harsh.RentRead.book.controller.exchanges.AddBookExchangeDto;
import com.harsh.RentRead.book.controller.exchanges.UpdateBookExchangeDto;
import com.harsh.RentRead.book.dto.BookDto;
import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.book.entity.enums.Genre;
import com.harsh.RentRead.book.repository.BookRepository;
import com.harsh.RentRead.book.service.implementations.BookServiceImplementaion;
import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BookServiceImplementationTest {

    @MockBean
    private BookRepository bookRepository;
    
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private BookServiceImplementaion bookService;

    @BeforeEach
    public void setUp() {
        bookService = new BookServiceImplementaion(bookRepository, modelMapper);
    }

    @Test
    @Description("IllegalArgumentException when Book Dto is null")
    public void addBookIllegalArgumentExceptionTest() {
        log.info("Starting addBookIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(null));
        log.info("addBookIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    public void addBookTest() {

        // request to service
        AddBookExchangeDto bookDto = new AddBookExchangeDto("Book", "Harsh", Genre.BIOGRAPHY);

        // responses from save
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setAvailabilityStatus(true);
        book.setGenre(bookDto.getGenre());
        book.setId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto response = bookService.addBook(bookDto);

        assertEquals(book.getId(), response.getId());
        assertEquals(book.getTitle(), response.getTitle());
        assertEquals(book.getAuthor(), response.getAuthor());
        assertEquals(book.getGenre(), response.getGenre());
    }

    @Test
    @Description("IllegalArgumentException when Book Dto is null")
    public void addAllBooksIllegalArgumentExceptionTest() {
        log.info("Starting addAllBooksIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> bookService.addAllBooks(null));
        log.info("addAllBooksIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("IllegalArgumentException when bookId is null")
    public void getBookByIdIllegalArgumentExceptionTest() {
        log.info("Starting getBookByIdIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> bookService.getBookById(null));
        log.info("getBookByIdIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException")
    public void getBookByIdResourceNotFoundExceptionTest() {
        log.info("Starting getBookByIdResourceNotFoundExceptionTest");

        when(bookRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(1L));
        log.info("getBookByIdResourceNotFoundExceptionTest completed successfully");
    }

    @Test
    @Description("IllegalArgumentException when bookId is null")
    public void deleteBookByIdIllegalArgumentExceptionTest() {
        log.info("Starting getBookByIdIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> bookService.deleteBookById(null));
        log.info("getBookByIdIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException")
    public void deleteBookByIdResourceNotFoundExceptionTest() {
        log.info("Starting getBookByIdResourceNotFoundExceptionTest");

        when(bookRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBookById(1L));
        log.info("getBookByIdResourceNotFoundExceptionTest completed successfully");
    }

    @Test
    @Description("IllegalArgumentException")
    public void updateBookIllegalArgumentExceptionTest() {
        log.info("Starting updateBookIllegalArgumentExceptionTest");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(null, null));
        assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(null, new UpdateBookExchangeDto()));
        assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(1L, null));
        log.info("updateBookIllegalArgumentExceptionTest completed successfully");
    }

    @Test
    @Description("ResourceNotFoundException")
    public void updateBookResourceNotFoundExceptionTest() {
        log.info("Starting updateBookResourceNotFoundExceptionTest");

        when(bookRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> bookService.updateBook(1L, new UpdateBookExchangeDto()));
        log.info("updateBookResourceNotFoundExceptionTest completed successfully");
    }
    

}
