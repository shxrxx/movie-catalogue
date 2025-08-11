# Movie Catalogue System

This is a Java Spring Boot application that fetches movie data from The Movie Database (TMDb) API and allows users to browse, search, and favorite movies. The application uses Thymeleaf for the frontend and an H2 in-memory database to store favorite movies.

## Features

- Display trending movies and search movies by title
- View detailed movie information
- Add and remove movies from favorites
- View a list of favorite movies
- Data persisted using H2 in-memory database

## Technologies Used

- Java 17+
- Spring Boot
- Spring MVC with Thymeleaf
- Spring Data JPA with H2 Database
- RestTemplate/WebClient for TMDb API integration
- Maven for build and dependency management

## Getting Started

### Prerequisites

- Java 17 or later installed
- Maven installed
- TMDb API key (you can obtain one at https://www.themoviedb.org/)

### Running Locally

1. Clone the repository
```
git clone https://github.com/yourusername/movie-catalogue.git
cd movie-catalogue 
```


2. Add your TMDb API key to src/main/resources/application.properties or set as environment variable:
```
tmdb.api.key=YOUR_TMDB_API_KEY
```

3. Build and run the app using Maven:
```
   mvn clean spring-boot:run
```
4.Open your browser at 
```
http://localhost:8080
```

## Deployment
This project can be deployed on any Java-compatible hosting environment or cloud provider supporting Spring Boot apps.

## License
MIT License

### Author
sharonzacharia21@gmail.com
