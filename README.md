# RentRead

RentRead is a RESTful API service developed using Spring Boot to manage an online book rental system. It allows users to register, login, browse books, rent books, return books, and manage user accounts. This document provides an overview of the application, its features, setup instructions, and other relevant information.

## Setup

To run this application locally, follow these steps:

1. Clone this repository:

   ```bash
   git clone https://github.com/harsh8999/RentRead.git
   ```

2. Build the application using Maven:
    ```bash
   mvnw clean install
   ```

3. Run the application:
    ```bash
    java -jar target/-0.0.1-SNAPSHOT.jar
    ```

## Images

![This is an alt text.](\src\main\resources\images\class-diagram.png "This is a sample image.")

## User Endpoints

- GET /api/v1/ - Welcome To RentRead.

### Public Endpoints
- POST /api/v1/auth/signup - Register a new user (Role can be ADMIN, USER), Default Role: USER.
    ### Request Body
    ```json
    {
    	"firstName": "Harsh",
        "lastName": "Nayak",
        "email" : "harsh@gmail.com",
        "password" : "1234",
        "role" : "ADMIN"
    }
    ```
    ### Request Body
    ```json
    {
    	"firstName": "Harsh",
        "lastName": "Nayak",
        "email" : "harsh@gmail.com",
        "password" : "1234"
    }
    ```
- POST /api/v1/auth/login - Login User.
    ### Request Body
    ```json
    {
        "email" : "harsh@gmail.com",
        "password" : "1234"
    }
    ```

## Private ADMIN Endpoints 
### Authorization: Basic Authentication required with ADMIN credentials


- GET /api/v1/users - Get all the Users
- PUT /api/v1/users/{user_id}/update - Update a user 
    ### Request Body
    ```json
    {
    	"firstName" : "Sahil",
        "lastName" : "Kumar",
        "email" : "sahil@gmail.com",
        "password" : "121233"
    }
    ```

- PUT /api/v1/users/{user_id}/addRole - Update Role of a user 
    ### Request Body
    ```json
    {
    	"role": "ADMIN"
    }
    ```
- DELETE /api/v1/users/{user_id} - Delete a User

## Book Endpoints
### Authorization: Basic Authentication required with user credentials

### Private Endpoints
- GET /api/v1/books - Get All Books
- GET /api/v1/books/{book_id} - Get Book By Id

### Private ADMIN Endpoints
- POST /api/v1/books - Add new Book 
    ### Request Body
    ```json
    {
        "title": "The Martian",
        "author": "Andy Weir",
        "genre": "SCIFI"
    }
    ```
- POST /api/v1/books/addAllBooks - Add List Of Books.
    ### Request Body
    ```json
    [
        {
            "title": "The Martian",
            "author": "Andy Weir",
            "genre": "SCIFI"
        },
        {
            
            "title": "Dune",
            "author": "Frank Herbert",
            "genre": "SCIFI"
        }, ...
    ]
    ```
- PUT /api/v1/books/{book_id}/update - Update Book Details
    ### Request Body
    ```json
    {
        "title": "The Martian",
        "author": "Andy Weir",
        "genre": "SCIFI"
    }
    ```
- DELETE /api/v1/books/{book_id}/delete - Delete a Book



## Class Diagram
https://lucid.app/lucidchart/46138806-4354-46a5-b75c-844a051a4537/edit?viewport_loc=-298%2C-78%2C2265%2C957%2C0_0&invitationId=inv_e6eb36ee-3cf5-4f23-9886-40b580ee6344


## Postman Collection
https://elements.getpostman.com/redirect?entityId=7585977-e633f6ae-9923-4d4c-a9ec-1ad9520b2ebd&entityType=collection

## License
Harsh Nayak