#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/open-maven-plugin-review .
docker rm -f open-maven-plugin-review || true && docker run -d -p 9080:9080 -p 9443:9443 --name open-maven-plugin-review de.rieckpil.blog/open-maven-plugin-review