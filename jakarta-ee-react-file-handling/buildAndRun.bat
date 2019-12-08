@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/jakarta-ee-react-file-handling .
call docker rm -f jakarta-ee-react-file-handling
call docker run -d -p 9080:9080 -p 9443:9443 --name jakarta-ee-react-file-handling de.rieckpil.blog/jakarta-ee-react-file-handling