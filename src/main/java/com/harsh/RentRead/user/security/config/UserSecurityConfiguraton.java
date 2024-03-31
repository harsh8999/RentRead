package com.harsh.RentRead.user.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class for user security settings.
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class UserSecurityConfiguraton {
    
    private UserRepository userRepository;

    /**
     * Defines a UserDetailsService bean that retrieves user details from the UserRepository.
     * 
     * @return The UserDetailsService bean
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            log.debug("Fetching user details for username: {}", username);
            return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", username));
        };
    }

    /**
     * Defines a PasswordEncoder bean for encoding and verifying passwords.
     * 
     * @return The PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an AuthenticationManager bean for handling authentication requests.
     * 
     * @param configuration The AuthenticationConfiguration for obtaining the authentication manager.
     * @return The AuthenticationManager bean
     * @throws Exception If an error occurs while obtaining the authentication manager.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        log.debug("Configuring AuthenticationManager");
        return configuration.getAuthenticationManager();
    }

    /**
     * Provides an AuthenticationProvider bean for authentication.
     * 
     * @return The AuthenticationProvider bean
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

  
}
