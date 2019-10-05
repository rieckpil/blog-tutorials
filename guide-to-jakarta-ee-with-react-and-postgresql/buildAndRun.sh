#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/guide-to-jakarta-ee-with-react-and-postgresql .
docker rm -f guide-to-jakarta-ee-with-react-and-postgresql || true && docker run -d -p 8080:8080 -p 4848:4848 --name guide-to-jakarta-ee-with-react-and-postgresql de.rieckpil.blog/guide-to-jakarta-ee-with-react-and-postgresql