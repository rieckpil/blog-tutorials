@echo off
call mvn clean package
call docker build -t debug-me .
call docker rm -f debug-me
call docker run -d -p 8080:8080 -p 8000:8000 --name debug-me debug-me