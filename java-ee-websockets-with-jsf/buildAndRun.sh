#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/java-ee-websockets-with-jsf .
docker rm -f java-ee-websockets-with-jsf || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-websockets-with-jsf de.rieckpil.blog/java-ee-websockets-with-jsf