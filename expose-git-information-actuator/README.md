# Codebase for the blog post [#HOWTO: Expose git information with Spring Boot’s Actuator](https://rieckpil.de/howto-expose-git-information-with-spring-boots-actuator/)

Steps to run this application:

1. Clone this Git repository
2. Navigate to the folder `expose-git-information-actuator`
3. Build the project with `mvn clean package`
4. Run the application with `java -jar target/expose-git-information-actuator-0.0.1-SNAPSHOT.jar`
5. Open the browser on `http://localhost:8080/actuator/info`