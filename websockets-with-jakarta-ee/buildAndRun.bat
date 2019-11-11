@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/websockets-with-jakarta-ee .
call docker rm -f websockets-with-jakarta-ee
call docker run -d -p 9080:9080 -p 9443:9443 --name websockets-with-jakarta-ee de.rieckpil.blog/websockets-with-jakarta-ee