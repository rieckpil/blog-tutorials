# traditional way of building Docker images for Spring Boot apllications
FROM openjdk:14-jdk-alpine
EXPOSE 8080
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","--enable-preview","/app.jar"]
