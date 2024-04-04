package com.harsh.RentRead.rent.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.book.repository.BookRepository;
import com.harsh.RentRead.rent.entity.Rental;
import com.harsh.RentRead.rent.repository.RentalRepository;
import com.harsh.RentRead.rent.services.implementations.RentalServiceImplementation;
import com.harsh.RentRead.user.entity.User;
import com.harsh.RentRead.user.services.UserAuthService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RentalServiceImplementationTest {

    @MockBean
    private RentalRepository rentalRepository;

    @MockBean
    private UserAuthService userAuthService;

    @MockBean
    private BookRepository bookRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private RentalServiceImplementation rentalService;

    @BeforeEach
    public void setUp() {
        rentalService = new RentalServiceImplementation(rentalRepository, userAuthService, bookRepository, modelMapper);
    }

    @Test
    @Description("IllegalStateException Test when user is trying to rent book more than 2")
    public void rentBook_IllegalStateException_Test() {

        log.info("Starting rentBook_IllegalStateException_Test");

        // User loggedInUser = userAuthService.getLoggedInUser();
        User loggedInUser = new User();
        loggedInUser.setEmail("email@gmail.com");
        loggedInUser.setPassword("123");
        loggedInUser.setId(1L);
        when(userAuthService.getLoggedInUser()).thenReturn(loggedInUser);

        // responses from save
        Book book = new Book();book.setAvailabilityStatus(true);
        book.setId(1L);

        when(bookRepository.findByIdAndAvailabilityStatusTrue(anyLong())).thenReturn(Optional.of(book));

        Rental rental = new Rental(1L, loggedInUser, book, null, true);
        Rental rental2 = new Rental(2L, loggedInUser, book, null, true);



        when(rentalRepository.findByUserIdAndActiveStatusTrue(loggedInUser)).thenReturn(Optional.of(List.of(rental, rental2)));

        
        assertThrows(IllegalStateException.class, () -> rentalService.rentBook(1L));
        log.info("rentBook_IllegalStateException_Test completed successfully");

    }
    
}
