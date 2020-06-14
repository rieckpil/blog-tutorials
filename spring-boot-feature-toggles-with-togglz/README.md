# Codebase for the blog post [Feature Toggles for Spring Boot applications with Togglz](https://rieckpil.de/howto-feature-toggles-for-spring-boot-applications-with-togglz/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-boot-feature-toggles-with-togglz`
3. Start the project with `mvn spring-boot:run`
4. Access both http://localhost:8080/books and  http://localhost:8080/books/wishlist
5. Change the state of a feature toggle at  http://localhost:8082/togglz-console and re-visit the previous endpoints
