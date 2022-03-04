#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "spring-boot-integration-tests-testcontainers"
 "telegram-bot-notifications-with-java"
 "websockets-with-jakarta-ee"
 "open-liberty-maven-plugin-review"
 "jakarta-ee-react-file-handling"
 "remote-debugging-spring-boot-application"
 "review-microshed-testing"
 "maven-plugins-to-ensure-quality"
 "spring-web-client-demo"
 "spring-boot-uploading-and-downloading-files-with-react"
 "spring-web-client-oauth2-reactive-stack"
 "spring-web-client-oauth2-servlet-stack"
 "spring-web-test-client"
 "spring-web-client-customizing"
 "spring-web-client-testing-with-mockwebserver"
 "spring-web-client-exchange-retrieve"
 "whats-new-in-microprofile-3.1"
 "whats-new-in-spring-boot-2.1"
 "whats-new-in-spring-boot-2.2"
 "spring-web-test-client"
 "github-actions-java-maven"
)

for project in "${arr[@]}"
do
  mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -f $project/pom.xml verify
done
