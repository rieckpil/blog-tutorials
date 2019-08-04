@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/java-ee-with-kotlin .
call docker rm -f java-ee-with-kotlin
call docker run -d -p 9080:9080 --name java-ee-with-kotlin de.rieckpil.blog/java-ee-with-kotlin