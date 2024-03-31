package com.harsh.RentRead.rent.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.harsh.RentRead.book.entity.Book;
import com.harsh.RentRead.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    
    // @ManyToOne: This annotation defines a many-to-one relationship between the Rental entity 
    // and the User and Book entities. It indicates that each rental is associated with one user 
    // and one book.
    @ManyToOne
    @JoinColumn(name = "user_id")
    User userId;

    
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book bookId;

    @CreationTimestamp
    @Column(updatable = false)
    Date rentalDate;

    // to check if book is still rented or not
    boolean activeStatus;

}
