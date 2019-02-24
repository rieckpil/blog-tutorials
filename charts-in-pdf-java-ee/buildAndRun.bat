@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/charts-in-pdf-java-ee .
call docker rm -f charts-in-pdf-java-ee
call docker run -d -p 9080:9080 --name charts-in-pdf-java-ee de.rieckpil.blog/charts-in-pdf-java-ee