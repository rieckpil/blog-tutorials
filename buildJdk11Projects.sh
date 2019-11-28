#!/bin/bash
declare -a arr=("whats-new-in-spring-boot-2.1" 
 "spring-web-client-demo"
 "spring-boot-hibernate-flyway-best-practices"
 "random-data-in-java-using-java-faker"
 "java-ee-with-kotlin"
 "spring-boot-feature-toggles-with-togglz"
 "spring-boot-integration-tests-wiremock")

for project in "${arr[@]}"
do
   mvn -B -f $project/pom.xml package
done