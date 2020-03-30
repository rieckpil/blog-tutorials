#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/jakarta-ee-microprofile .
docker rm -f jakarta-ee-microprofile || true && docker run -d -p 9080:9080 -p 9443:9443 --name jakarta-ee-microprofile de.rieckpil.learning/jakarta-ee-microprofile
