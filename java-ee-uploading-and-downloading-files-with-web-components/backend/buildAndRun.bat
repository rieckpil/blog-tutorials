@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/backend .
call docker rm -f backend
call docker run -d -p 8080:8080 -p 4848:4848 --name backend de.rieckpil.blog/backend