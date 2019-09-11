@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/bootstrap-jakarta-ee-8-application .
call docker rm -f bootstrap-jakarta-ee-8-application
call docker run -d -p 8080:8080 -p 9990:9990 --name bootstrap-jakarta-ee-8-application de.rieckpil.blog/bootstrap-jakarta-ee-8-application