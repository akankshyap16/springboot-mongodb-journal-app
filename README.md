# Spring Boot MongoDB Journal App

A RESTful Journal Application built using Spring Boot and MongoDB.

## Features

### User Management
- Create User
- Get All Users
- Update User by Username
- Delete User by Username

### Journal Management
- Create Journal Entry
- Get All Journal Entries of a User
- Get Journal Entry by ID
- Delete Journal Entry by ID
- Delete All Journal Entries of a User

### Relationships
- User ↔ JournalEntry mapping using MongoDB DBRef

## Tech Stack

- Java
- Spring Boot
- MongoDB
- Spring Data MongoDB
- Lombok
- Maven

## API Endpoints

### User APIs

- GET `/user`
- POST `/user`
- PUT `/user/{userName}`
- DELETE `/user/{userName}`

### Journal APIs

- GET `/journal/{userName}`
- POST `/journal/{userName}`
- GET `/journal/id/{myid}`
- DELETE `/journal/id/{userName}/{myid}`
- DELETE `/journal/name/{userName}`

## Future Improvements

- Transactions
- MongoDB Atlas
- Spring Security
- Authentication & Authorization
- Deployment
