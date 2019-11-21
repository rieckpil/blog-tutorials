#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/review-microshed-testing .
docker rm -f review-microshed-testing || true && docker run -d -p 9080:9080 -p 9443:9443 --name review-microshed-testing de.rieckpil.blog/review-microshed-testing