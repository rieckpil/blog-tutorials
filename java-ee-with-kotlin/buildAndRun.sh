#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/java-ee-with-kotlin .
docker rm -f java-ee-with-kotlin || true && docker run -d -p 9080:9080 --name java-ee-with-kotlin de.rieckpil.blog/java-ee-with-kotlin