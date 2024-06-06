#!/bin/bash

set -e
set -o pipefail

mvn -B -f consumer-driven-contracts-with-spring-cloud-contract/book-store-server install

declare -a arr=(
 "charts-in-pdf-java-ee"
 "java-benchmarking-with-jmh"
 "graalvm-intro"
 "bootstrap-jakarta-ee-8-application"
 "custom-maven-archetype"
 "guide-to-jakarta-ee-with-react-and-postgresql"
 "five-java-9-features"
 "consumer-driven-contracts-with-spring-cloud-contract/book-store-client"
 "java-ee-with-kotlin"
 "test-java-http-clients"
  "telegram-bot-notifications-with-java"
  "websockets-with-jakarta-ee"
  "open-liberty-maven-plugin-review"
  "jakarta-ee-react-file-handling"
  "review-microshed-testing"
  "whats-new-in-microprofile-3.1"
  "serverless-java-aws-examples/thumbnail-generator"
  "serverless-java-aws-examples/spring-cloud-function-aws"
  "serverless-java-aws-examples/spring-cloud-function-kotlin-aws"
  "five-unknown-junit-5-features"
  "mockito-tips-and-tricks"
  "testing-java-applications-with-maven"
  "open-rewrite-example"
 )

for project in "${arr[@]}"
do
  mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -f $project/pom.xml verify
done
