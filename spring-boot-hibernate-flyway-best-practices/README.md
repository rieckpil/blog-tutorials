# Codebase for the blog post [Best Practices Flyway and Hibernate with Spring Boot](https://rieckpil.de/howto-best-practices-for-flyway-and-hibernate-with-spring-boot/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-boot-hibernate-flyway-best-practices`
3. Provide a local PostgreSQL database (user: `postgres`, password: `postgres`) or adjust the configuration within the `application.yml`
4. Start the application with: `mvn spring-boot:run` to apply the migration scripts to the database
5. Start with profile `generatesql` to create the DDL out of the JPA model: `mvn -Dspring.profiles.active=generatesql spring-boot:run`
