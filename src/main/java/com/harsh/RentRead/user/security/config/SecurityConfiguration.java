package com.harsh.RentRead.user.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class for defining security settings for the application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfiguration {

    public AuthenticationProvider authenticationProvider;
    
    public SecurityConfiguration(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * Defines a SecurityFilterChain for processing HTTP security configurations.
     * 
     * @param httpSecurity The HttpSecurity object for configuring HTTP security.
     * @return The SecurityFilterChain for processing security configurations.
     * @throws Exception If an error occurs while configuring HTTP security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.debug("Configuring security filter chain");
        httpSecurity
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> 
                auth.requestMatchers("/", "/api/v1/auth/**").permitAll()
                .anyRequest().authenticated())
            .authenticationProvider(authenticationProvider);

        return httpSecurity.build();
    }
}
