# Codebase for the blog post [Consumer-Driven Contracts with Spring Cloud Contract](https://rieckpil.de/howto-consumer-driven-contracts-with-spring-cloud-contract/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `consumer-driven-contracts-with-spring-cloud-contract`
3. Navigate to the `book-store-server` folder and build the project with `mvn install`
4. Navigate to the `book-store-client` folder and execute `mvn test`. The tests should pass and make use of the `book-store-server` stubs
