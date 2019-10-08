@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/guide-to-jakarta-ee-with-react-and-postgresql .
call docker rm -f guide-to-jakarta-ee-with-react-and-postgresql
call docker run -d -p 9080:9080 -p 9443:9443 --name guide-to-jakarta-ee-with-react-and-postgresql de.rieckpil.blog/guide-to-jakarta-ee-with-react-and-postgresql