FROM openjdk:11.0.16-jdk
VOLUME /tmp
COPY target/deploy-spring-boot-to-gke.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]