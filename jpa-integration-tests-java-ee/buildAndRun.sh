#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/jpa-integration-tests-java-ee .
docker rm -f jpa-integration-tests-java-ee || true && docker run -d -p 8080:8080 -p 4848:4848 --name jpa-integration-tests-java-ee de.rieckpil.blog/jpa-integration-tests-java-ee