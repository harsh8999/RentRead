package com.harsh.RentRead.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.harsh.RentRead.exception.exceptions.ResourceNotFoundException;
import com.harsh.RentRead.exception.exchangeDtos.ExceptionApiResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for handling exceptions thrown within controller methods.
 * This class provides methods to handle specific types of exceptions and return appropriate HTTP responses.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns an HTTP 404 (Not Found) response.
     * 
     * @param ex The ResourceNotFoundException to handle
     * @return ResponseEntity containing an ExceptionApiResponse with the error message and HTTP status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        log.error("ResourceNotFoundException: {}", message, ex);
        ExceptionApiResponse apiResponse = new ExceptionApiResponse(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles IllegalStateException and IllegalArgumentException and returns an HTTP 404 (Not Found) response.
     * 
     * @param ex The IllegalStateException to handle.
     * @return ResponseEntity containing an ExceptionApiResponse with the error message and HTTP status.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionApiResponse> illegalStateExceptionHandler(Exception ex){
        String message = ex.getMessage();
        log.error("IllegalStateException: {}", message, ex);
        ExceptionApiResponse apiResponse = new ExceptionApiResponse(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles IllegalStateException and IllegalArgumentException and returns an HTTP 404 (Not Found) response.
     * 
     * @param ex The IllegalArgumentException to handle.
     * @return ResponseEntity containing an ExceptionApiResponse with the error message and HTTP status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionApiResponse> illegalArgumentExceptionHandler(Exception ex){
        String message = ex.getMessage();
        log.error("IllegalArgumentException: {}", message, ex);
        ExceptionApiResponse apiResponse = new ExceptionApiResponse(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
