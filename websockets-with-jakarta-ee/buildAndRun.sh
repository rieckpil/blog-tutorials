#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/websockets-with-jakarta-ee .
docker rm -f websockets-with-jakarta-ee || true && docker run -d -p 9080:9080 -p 9443:9443 --name websockets-with-jakarta-ee de.rieckpil.blog/websockets-with-jakarta-ee