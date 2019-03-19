#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/application-server-cheatsheet .
docker rm -f application-server-cheatsheet || true && docker run -d -p 8080:8080 -p 4848:4848 --name application-server-cheatsheet de.rieckpil.blog/application-server-cheatsheet