FROM openjdk:11-jdk
VOLUME /tmp
COPY target/deploy-spring-boot-to-gke.jar app.jar
ENV message="Hello World"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]