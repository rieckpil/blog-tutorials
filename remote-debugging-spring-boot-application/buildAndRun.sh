#!/bin/sh
mvn clean package && docker build -t debug-me .
docker rm -f debug-me || true && docker run -d -p 8080:8080 -p 8000:8000 --name debug-me debug-me