#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/java-magazin-jsf-primefaces .
docker rm -f java-magazin-jsf-primefaces || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-magazin-jsf-primefaces de.rieckpil.blog/java-magazin-jsf-primefaces