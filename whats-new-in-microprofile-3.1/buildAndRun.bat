@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/whats-new-in-microprofile-3.1 .
call docker rm -f whats-new-in-microprofile-3.1
call docker run -d -p 9080:9080 -p 9443:9443 --name whats-new-in-microprofile-3.1  de.rieckpil.blog/whats-new-in-microprofile-3.1
