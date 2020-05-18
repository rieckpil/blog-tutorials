# Codebase for the blog post [What's new in Spring Boot 2.3](https://rieckpil.de/whats-new-in-spring-boot-2-3/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `whats-new-in-spring-boot-2-3`
3. Ensure you have Java 14 installed `java -version`
4. Build the application with `mvn package`
5. Start the Docker container:
```
docker run -p 8080:8080 -e JAVA_OPTS='--enable-preview' myregistry.com/rieckpil/whats-new-in-spring-boot-2-3:latest
```

... and access e.g. http://localhost:8080/users
