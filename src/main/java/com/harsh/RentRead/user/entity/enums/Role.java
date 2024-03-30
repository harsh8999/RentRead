package com.harsh.RentRead.user.entity.enums;

/**
 * Enum representing the roles of users in the system.
 * Users can have either USER or ADMIN roles.
 */
public enum Role {

    /**
     * Represents a regular user role.
     * Users with this role have limited privileges.
     */
    USER,

    /**
     * Represents an administrator role.
     * Users with this role have elevated privileges,
     * such as managing books and users.
     */
    ADMIN
}
