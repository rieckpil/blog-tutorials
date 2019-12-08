#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/jakarta-ee-react-file-handling .
docker rm -f jakarta-ee-react-file-handling || true && docker run -d -p 9080:9080 -p 9443:9443 --name jakarta-ee-react-file-handling de.rieckpil.blog/jakarta-ee-react-file-handling