#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/rest-easy-file-uploading-and-downloading .
docker rm -f rest-easy-file-uploading-and-downloading || true && docker run -d -p 8080:8080 -p 9990:9990 --name rest-easy-file-uploading-and-downloading de.rieckpil.blog/rest-easy-file-uploading-and-downloading