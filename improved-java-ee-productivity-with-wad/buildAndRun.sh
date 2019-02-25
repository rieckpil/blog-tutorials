#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/improved-java-ee-productivity-with-wad .
docker rm -f improved-java-ee-productivity-with-wad || true && docker run -d -p 8080:8080 -p 4848:4848 --name improved-java-ee-productivity-with-wad de.rieckpil.blog/improved-java-ee-productivity-with-wad