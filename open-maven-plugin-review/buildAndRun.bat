@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/open-maven-plugin-review .
call docker rm -f open-maven-plugin-review
call docker run -d -p 9080:9080 -p 9443:9443 --name open-maven-plugin-review de.rieckpil.blog/open-maven-plugin-review