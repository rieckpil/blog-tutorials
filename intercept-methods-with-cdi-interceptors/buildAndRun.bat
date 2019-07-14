@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/intercept-methods-with-cdi-interceptors .
call docker rm -f intercept-methods-with-cdi-interceptors
call docker run -d -p 8080:8080 -p 4848:4848 --name intercept-methods-with-cdi-interceptors de.rieckpil.blog/intercept-methods-with-cdi-interceptors