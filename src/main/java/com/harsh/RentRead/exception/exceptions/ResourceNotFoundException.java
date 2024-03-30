package com.harsh.RentRead.exception.exceptions;

/**
 * Custom exception class to indicate that a resource was not found.
 * This exception is typically thrown when attempting to access a resource that does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * The name of the resource that was not found.
     */
    String resourseName;

    /**
     * The name of the field associated with the resource.
     */
    String fieldName;
    
    /**
     * The value of the field associated with the resource.
     */
    String fieldValue;
    
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param resourceName the name of the resource that was not found
     * @param fieldName    the name of the field associated with the resource
     * @param fieldValue   the value of the field associated with the resource
     */
    public ResourceNotFoundException(String resourseName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s: %s", resourseName, fieldName, fieldValue));
        this.resourseName = resourseName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
}
