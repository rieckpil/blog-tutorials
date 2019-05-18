# Codebase for the blog post [#HOWTO: Remote Debug Spring Boot applications (IntelliJ IDEA + Eclipse)](https://rieckpil.de/howto-remote-debug-spring-boot-applications-intellij-idea-eclipse/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `remote-debugging-spring-boot-appliction`
3. Build the project with `mvn clean package`
4. Run the project locally with `java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000 -jar target/remote-debugging-spring-boot-application.jar`
5. Follow the steps described in the blog post to remote debug the application