# Codebase for the blog post [#HOWTO:  Deploy a Spring Boot Uber-Jar application within Open Liberty](https://rieckpil.de/howto-run-spring-boot-uber-jar-application-within-open-liberty/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-boot-with-open-liberty`
3. Build the project with `mvn clean package`
4. Start your Docker daemon
5. Run `docker build -t spring-liberty .`
6. Run `docker run -p 9080:9080 spring-liberty` and wait until the applications successfully booted
7. Visit `http://localhost:9080` for chatting and `http://localhost:9080/api/messages` to get all chat messages