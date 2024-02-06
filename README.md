# SpringBoot-FlightSearch-REST-API

🚀 Welcome to the Flight Search API documentation! 🚀

## Table of Contents
1. [Introduction](#introduction)
2. [Project Overview](#project-overview)
3. [Technologies Used](#technologies-used)
4. [Project Structure](#project-structure)
5. [Data Modeling](#data-modeling)
6. [CRUD Operations](#crud-operations)
7. [Custom Search API](#custom-search-api)
8. [Authentication](#authentication)
9. [Scheduled Background Jobs](#scheduled-background-jobs)
10. [Learning Outcomes](#learning-outcomes)

## Introduction
This repository contains the implementation of a Flight Search API built using Spring Boot. The API allows users to search for flights based on various criteria such as departure airport, arrival airport, departure date, and return date.

## Project Overview
The Flight Search API project aims to provide a RESTful interface for flight-related information. It includes features like data modeling, CRUD operations for flights and airports, a custom search endpoint, authentication, and scheduled background jobs for data updates.

## Technologies Used
The project leverages several technologies and frameworks to achieve its goals, including:
- **Java:** The primary programming language used for development.
- **Spring Boot:** A powerful framework for building robust and scalable Java applications.
- **Spring Security:** The Spring Security framework is used to implement authentication and authorization features, ensuring secure access to the API.
- **MongoDB:** A NoSQL database used to store flight and airport data, providing flexibility and scalability.
- **Swagger:** Used for API documentation, making it easier for developers to understand and interact with the API.

## Project Structure
The project is organized into different folders and files, following best practices for a Spring Boot application:
- `config`: Configuration files for the Spring Boot application, including security configurations.
- `controller`: Controllers responsible for handling HTTP requests and routing them to the appropriate services.
- `model`: Data models for flights and airports, defining their structure and relationships.
- `repository`: Database repositories for performing CRUD operations on flight and airport entities.
- `scheduled`: Scheduled background jobs that run daily to fetch and update flight data from a third-party API (for development purposes, a mock API can be used).
- `service`: Business logic and service classes that handle the core functionality of the application.
- `FlightSearchApiApplication.java`: The main application class that initializes and runs the Spring Boot application.

## Data Modeling
The project uses MongoDB, a NoSQL database, to store flight and airport information. Here's a more detailed overview of the data models:
- `Flight`:
  - ID: Unique identifier for each flight.
  - Departure Airport: The airport from which the flight departs.
  - Arrival Airport: The airport to which the flight arrives.
  - Departure Date/Time: The date and time when the flight departs.
  - Return Date/Time: The date and time for return flights (null for one-way flights).
  - Price: The cost of the flight.
- `Airport`:
  - ID: Unique identifier for each airport.
  - City: The city to which the airport belongs.

## CRUD Operations
The API supports CRUD (Create, Read, Update, Delete) operations for flights and airports, allowing users to manage the database records efficiently.

## Custom Search API
One of the key features of this API is the custom search endpoint. Users can search for flights based on their specific criteria, including departure airport, arrival airport, departure date, and return date. If a return date is provided, the API returns round-trip flights; otherwise, it returns one-way flights.

## Authentication
Security is a top priority for this API, and it's achieved using the Spring Security framework. Authentication ensures that only authorized users can access certain API endpoints. Various authentication methods and configurations can be applied based on specific requirements. 

## Scheduled Background Jobs
To provide up-to-date flight information, the project includes a scheduled background job. This job runs daily and fetches flight data from a third-party API (for development purposes, a mock API can be used) and stores it in the MongoDB database. This ensures that the API always offers the latest flight details.

## Learning Outcomes
By working on this project, you will gain valuable experience and knowledge in the following areas:
- Developed a RESTful API using Spring Boot, covering all aspects of API development.
- Implemented data modeling for flights and airports in a NoSQL database (MongoDB).
- Created and managed CRUD operations for flight and airport entities.
- Designed and implemented a custom search API endpoint to serve user-specific queries.
- Implemented robust security using Spring Security for authentication and authorization.
- Developed and scheduled background jobs for data updates, ensuring real-time information for users.

Feel free to reach out to eakkaya21@ku.edu.tr if you have any questions or need assistance with this project.🌟🛫

