package com.harsh.RentRead.user.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.harsh.RentRead.user.entity.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Entity class representing a user in the system.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements UserDetails {
    
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    /**
     * The first name of the user.
     */
    @Column(nullable = false)
    String firstName;

    /**
     * The last name of the user.
     */
    String lastName;

    /**
     * The email address of the user.
     * This also serves as the username.
     */
    @Column(unique = true, length = 100, nullable = false)
    String email;

    /**
     * The encrypted password of the user.
     */
    @Column(nullable = false)
    String password;

    /**
     * The role of the user (e.g., USER or ADMIN).
     */
    Role role;

    /**
     * The date and time when the user was created.
     * This field is automatically managed by the database.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    Date createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
