@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/jakarta-ee-microprofile .
call docker rm -f jakarta-ee-microprofile
call docker run -d -p 9080:9080 -p 9443:9443 --name demo de.rieckpil.learning/jakarta-ee-microprofile
