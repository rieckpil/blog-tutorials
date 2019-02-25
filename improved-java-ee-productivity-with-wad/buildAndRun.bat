@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/improved-java-ee-productivity-with-wad .
call docker rm -f improved-java-ee-productivity-with-wad
call docker run -d -p 8080:8080 -p 4848:4848 --name improved-java-ee-productivity-with-wad de.rieckpil.blog/improved-java-ee-productivity-with-wad