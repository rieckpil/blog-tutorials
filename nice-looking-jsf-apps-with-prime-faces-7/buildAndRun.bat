@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/nice-looking-jsf-apps-with-prime-faces-7 .
call docker rm -f nice-looking-jsf-apps-with-prime-faces-7
call docker run -d -p 8080:8080 -p 4848:4848 --name nice-looking-jsf-apps-with-prime-faces-7 de.rieckpil.blog/nice-looking-jsf-apps-with-prime-faces-7