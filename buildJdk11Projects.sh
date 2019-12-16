#!/bin/bash
declare -a arr=("whats-new-in-spring-boot-2.1" 
 "spring-web-client-demo"
 "spring-boot-hibernate-flyway-best-practices"
 "random-data-in-java-using-java-faker"
 "java-ee-with-kotlin"
 "spring-boot-feature-toggles-with-togglz"
 "spring-boot-integration-tests-wiremock"
 "telegram-bot-noficiations-with-java"
 "websockets-with-jakarta-ee"
 "review-microshed-testing"
 "spring-web-client-demo"
 "spring-web-client-oauth2-reactive-stack"
 "spring-web-client-oauth2-servlet-stack"
 "spring-web-test-client"
 "whats-new-in-microprofile-3.1"
 "whats-new-in-spring-boot-2.1"
 "whats-new-in-spring-boot-2.2"
 "spring-boot-with-kotlin"
 "spring-web-test-client")

for project in "${arr[@]}"
do
   mvn -B -f $project/pom.xml verify
done