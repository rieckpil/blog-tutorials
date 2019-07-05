#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/java-ee-kubernetes-deployment .
docker rm -f java-ee-kubernetes-deployment || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-kubernetes-deployment de.rieckpil.blog/java-ee-kubernetes-deployment