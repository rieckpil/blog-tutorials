@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/microprofile-jwt-keycloak-auth .
call docker rm -f microprofile-jwt-keycloak-auth
call docker run -d -p 8080:8080 -p 4848:4848 --name microprofile-jwt-keycloak-auth de.rieckpil.learning/microprofile-jwt-keycloak-auth