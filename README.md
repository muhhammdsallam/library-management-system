# Library Management System with Spring Boot

## Running the Application

**1- Clone the Repository**: If you haven't already, clone the repository.
   ```bash
   git clone https://github.com/muhhammdsallam/library-management-system.git
  ```
**2- Make sure that postgres is installed and running locally on port 5432 with database named lms**

## Interacting with API Endpoints

### Base URL

All API endpoints are accessible under the following base URL: **http://localhost:8090**

### 1- Books
- Get All Books
  ``` bash
  GET http://localhost:8090/api/books
  ```
  Response
  ``` bash
  [
    {
        "id": 1,
        "title": "To Kill a Mockingbird",
        "author": "Harper Lee",
        "publicationYear": 1960,
        "isbn": "9780060935467"
    },
    {
        "id": 2,
        "title": "1984",
        "author": "George Orwell",
        "publicationYear": 1949,
        "isbn": "9780451524935"
    },
    .
    .
  ]
  ```
- Get Book by ID
  ``` bash
  GET http://localhost:8090/api/books/1
  ```
  Response
  ```bash
  {
        "id": 1,
        "title": "To Kill a Mockingbird",
        "author": "Harper Lee",
        "publicationYear": 1960,
        "isbn": "9780060935467"
    }
  ```
- Add a Book
  ``` bash
  POST http://localhost:8090/api/books
  ```
  Request Body
  ```bash
  {
    "title": "Pride and Prejudice",
    "author": "Jane Austen",
    "publicationYear": 1813,
    "isbn": "9780141439518"
  }
  ```
  Response
  ```bash
  {
    "id": 1,
    "title": "Pride and Prejudice",
    "author": "Jane Austen",
    "publicationYear": 1813,
    "isbn": "9780141439518"
  }
  ```
- Update Book
  ```bash
  PUT http://localhost:8090/api/books/8
  ```
  Request Body
  ```bash
  {
    "title": "The Great Gatsby UPDATED",
    "author": "F. Scott Fitzgerald",
    "publicationYear": 1925,
    "isbn": "9780743273565"
  }
  ```
  Response
  ```bash
  {
    "id": 8,
    "title": "The Great Gatsby UPDATED",
    "author": "F. Scott Fitzgerald",
    "publicationYear": 1925,
    "isbn": "9780743273565"
  }
  ```
- Delete a Book
  ```bash
  DELETE http://localhost:8090/api/books/10
  ```
### 2- Patrons
- Get All Patrons
   ``` bash
  GET http://localhost:8090/api/patrons
  ```
  Response
  ``` bash
  [
    {
        "id": 1,
        "name": "John Doe",
        "mobileNumber": "1234567890"
    },
    {
        "id": 2,
        "name": "Jane Smith",
        "mobileNumber": "0987654321"
    },
    .
    .
  ]
  ```
- Get Patron by ID
  ``` bash
  GET http://localhost:8090/api/patrons/1
  ```
  Response
  ```bash
  {
    "id": 1,
    "name": "John Doe",
    "mobileNumber": "1234567890"
  }
  ```
- Add a Patron
  ``` bash
  POST http://localhost:8090/api/patrons
  ```
  Request Body
  ```bash
  {
    "name": "Mohamed Sallam",
    "mobileNumber": "1234567891"
  }
  ```
  Response
  ```bash
  {
    "id": 11,
    "name": "Mohamed Sallam",
    "mobileNumber": "1234567891"
  }
  ```
- Update a Patron
  ```bash
  PUT http://localhost:8090/api/patrons/8
  ```
  Request Body
  ```bash
  {
    "name": "Fiona Green UPDATED",
    "mobileNumber": "6789012345"
  }
  ```
  Response
  ```bash
  {
    "id": 8,
    "name": "Fiona Green UPDATED",
    "mobileNumber": "6789012345"
  }
  ```
- Delete a Patron
  ```bash
  DELETE http://localhost:8090/api/patrons/10
  ```
### 3- Borrow
- Borrow a Book
  ```bash
  POST http://localhost:8090/api/borrow/1/patron/2
  ```
  Response
  ```bash
  {
    "id": 1,
    "bookId": 1,
    "patronId": 2,
    "borrowingDate": "2024-08-16",
    "returnDate": null
  }
  ```
### 4- Return
- Return a Book
  ```bash
  PUT http://localhost:8090/api/return/1/patron/2
  ```
  Reponse
  ```bash
  {
    "id": 1,
    "bookId": 1,
    "patronId": 2,
    "borrowingDate": "2024-08-16",
    "returnDate": "2024-08-16"
  }
  ```
