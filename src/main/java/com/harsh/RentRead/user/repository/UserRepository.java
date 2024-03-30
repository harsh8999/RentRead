package com.harsh.RentRead.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.RentRead.user.entity.User;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Retrieves a user by their email address.
     * @param email The email address of the user.
     * @return An Optional containing the user if found, or empty otherwise.
     */
    Optional<User> findByEmail(String email);

}
