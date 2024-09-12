# rak-backend-app
# Overview
This project is a Spring Boot application designed to manage user accounts with a focus on RESTful APIs. The application follows an API First design approach using Swagger specifications, hashes passwords before storing them in the database, and employs comprehensive input validation. It uses an H2 in-memory database for simplicity and includes automated Swagger documentation.
# Features
RESTful APIs: Implemented for account management.
Password Hashing: Passwords are hashed before being stored in the database.
Input Validations:
Name: Mandatory, maximum length of 50 characters.
Email: Mandatory, must be in a valid email format.
Password: Mandatory, minimum length of 8 characters, should only contain alphabets and numbers.
Swagger Documentation: Auto-generated based on the latest code.

# Technologies Used

Spring Boot: For building the application.
H2 Database: In-memory database for development and testing.
Swagger: For API documentation.
JUnit & Mockito: For unit testing.

# Getting Started

# Prerequisites
Java 17 or later
Maven

# Setup
# Clone the Repository
git clone https://github.com/sravansasi24/rak-backend-app.git

cd your-repository

# Build the Application
mvn clean install

# Run the Application
mvn spring-boot:run

# ccess the Swagger UI
Navigate to http://localhost:8080/swagger-ui.html to interact with the API documentation.



