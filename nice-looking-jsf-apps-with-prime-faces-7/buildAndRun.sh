#!/bin/bash
mvn clean package && docker build -t de.rieckpil.blog/nice-looking-jsf-apps-with-prime-faces-7 .
docker rm -f nice-looking-jsf-apps-with-prime-faces-7 || true && docker run -d -p 8080:8080 -p 4848:4848 --name nice-looking-jsf-apps-with-prime-faces-7 de.rieckpil.blog/nice-looking-jsf-apps-with-prime-faces-7
