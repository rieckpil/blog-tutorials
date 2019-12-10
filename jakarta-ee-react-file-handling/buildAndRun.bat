@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/jakarta-ee-react-file-handling .
call docker rm -f jakarta-ee-react-file-handling
call docker run -d -p 8080:8080 -p 4848:4848 --name jakarta-ee-react-file-handling de.rieckpil.blog/jakarta-ee-react-file-handling
