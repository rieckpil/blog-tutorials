#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/backend .
docker rm -f backend || true && docker run -d -p 8080:8080 -p 4848:4848 --name backend de.rieckpil.blog/backend