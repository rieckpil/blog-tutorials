#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/jakarta-ee-react-file-handling .
docker rm -f jakarta-ee-react-file-handling || true && docker run -d -p 8080:8080 -p 4848:4848 --name jakarta-ee-react-file-handling de.rieckpil.blog/jakarta-ee-react-file-handling
