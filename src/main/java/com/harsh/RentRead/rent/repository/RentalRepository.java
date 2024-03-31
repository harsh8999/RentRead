package com.harsh.RentRead.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.rent.entity.Rental;
import com.harsh.RentRead.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<List<Rental>> findByUserIdAndActiveStatusTrue(User user); 
    Optional<List<Rental>> findByUserId(User user);
    Optional<Rental> findByUserIdAndBookIdAndActiveStatusTrue(User user, Book book); 
}
