@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/guide-to-jakarta-ee-with-react-and-postgresql .
call docker rm -f guide-to-jakarta-ee-with-react-and-postgresql
call docker run -d -p 8080:8080 -p 4848:4848 --name guide-to-jakarta-ee-with-react-and-postgresql de.rieckpil.blog/guide-to-jakarta-ee-with-react-and-postgresql