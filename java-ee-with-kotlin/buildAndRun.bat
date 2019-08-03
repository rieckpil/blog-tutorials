@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/java-ee-with-kotlin .
call docker rm -f java-ee-with-kotlin
call docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-with-kotlin de.rieckpil.blog/java-ee-with-kotlin