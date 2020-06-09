@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/crud-table-jsf-primefaces .
call docker rm -f crud-table-jsf-primefaces
call docker run -d -p 8080:8080 -p 4848:4848 --name crud-table-jsf-primefaces de.rieckpil.blog/crud-table-jsf-primefaces