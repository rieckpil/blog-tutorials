#!/bin/bash
declare -a arr=("whats-new-in-spring-boot-2.1" 
 "bootstrap-jakarta-ee-8-application"
 "custom-maven-archetype"
 "demo-crud-application"
 "deploy-spring-boot-to-gke"
 "spring-boot-hibernate-flyway-best-practices"
 "random-data-in-java-using-java-faker"
 "guide-to-jakarta-ee-with-react-and-postgresql"
 "five-java-9-features"
 "java-ee-with-kotlin"
 "spring-boot-with-kotlin"
 "spring-boot-feature-toggles-with-togglz"
 "spring-boot-integration-tests-wiremock"
 "telegram-bot-notifications-with-java"
 "websockets-with-jakarta-ee"
 "jakarta-ee-react-file-handling"
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
  if [[ "$?" -ne 0 ]] ; then
    echo "Failed to build project: $project"; exit $rc
  fi
done