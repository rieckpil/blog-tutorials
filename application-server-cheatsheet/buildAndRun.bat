@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/application-server-cheatsheet .
call docker rm -f application-server-cheatsheet
call docker run -d -p 8080:8080 -p 4848:4848 --name application-server-cheatsheet de.rieckpil.blog/application-server-cheatsheet