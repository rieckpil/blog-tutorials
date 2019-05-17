FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/remote-debugging-spring-boot-application.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000","-jar","/app.jar"]