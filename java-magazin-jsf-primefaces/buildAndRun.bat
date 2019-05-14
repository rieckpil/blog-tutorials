@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/java-magazin-jsf-primefaces .
call docker rm -f java-magazin-jsf-primefaces
call docker run -d -p 8080:8080 -p 4848:4848 --name java-magazin-jsf-primefaces de.rieckpil.blog/java-magazin-jsf-primefaces