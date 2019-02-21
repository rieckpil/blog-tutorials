#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/charts-in-pdf-java-ee .
docker rm -f charts-in-pdf-java-ee || true && docker run -d -p 8080:8080 -p 4848:4848 --name charts-in-pdf-java-ee de.rieckpil.blog/charts-in-pdf-java-ee