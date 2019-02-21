@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/charts-in-pdf-java-ee .
call docker rm -f charts-in-pdf-java-ee
call docker run -d -p 8080:8080 -p 4848:4848 --name charts-in-pdf-java-ee de.rieckpil.blog/charts-in-pdf-java-ee