package com.harsh.RentRead.book.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.harsh.RentRead.book.entity.enums.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Entity class representing a book.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    /**
     * The unique identifier of the book.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    /**
     * The title of the book.
     */
    String title;

    /**
     * The author of the book.
     */
    String author;

    /**
     * The genre of the book.
     */
    @Enumerated(EnumType.STRING)
    Genre genre;

    /**
     * The availability status of the book.
     */
    boolean availabilityStatus;

    /**
     * The timestamp when the book was created.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    Date createdAt;

}
