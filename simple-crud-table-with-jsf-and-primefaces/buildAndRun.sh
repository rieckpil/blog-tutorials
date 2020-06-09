#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/crud-table-jsf-primefaces .
docker rm -f crud-table-jsf-primefaces || true && docker run -d -p 8080:8080 -p 4848:4848 --name crud-table-jsf-primefaces de.rieckpil.blog/crud-table-jsf-primefaces