@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/review-microshed-testing .
call docker rm -f review-microshed-testing
call docker run -d -p 9080:9080 -p 9443:9443 --name review-microshed-testing de.rieckpil.blog/review-microshed-testing