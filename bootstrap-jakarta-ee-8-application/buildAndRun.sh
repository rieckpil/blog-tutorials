#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/bootstrap-jakarta-ee-8-application .
docker rm -f bootstrap-jakarta-ee-8-application || true && docker run -d -p 8080:8080 -p 9990:9990 --name bootstrap-jakarta-ee-8-application de.rieckpil.blog/bootstrap-jakarta-ee-8-application