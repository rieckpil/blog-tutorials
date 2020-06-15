#!/bin/sh

mvn clean package && docker build -t de.rieckpil.blog/charts-in-pdf-java-ee .
docker rm -f charts-in-pdf-java-ee || true && docker run -d -p 9080:9080 --name charts-in-pdf-java-ee de.rieckpil.blog/charts-in-pdf-java-ee
