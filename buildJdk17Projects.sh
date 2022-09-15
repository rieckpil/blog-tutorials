#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "testing-spring-rest-template"
 "spring-boot-datajpatest"
 "spring-boot-aws-integration-tests"
 "serverless-java-aws-examples/java-aws-lambda-custom-image"
 "spring-boot-integration-tests-wiremock"
 "launchdarkly-java-development-testing-hints"
 "spring-boot-web-mvc-testing"
 "spring-cloud-aws-sqs-testing"
 "spring-websocket-integration-tests"
 "spring-boot-test-mockmvc-webtestclient-testresttemplate"
 "random-data-in-java-using-java-faker"
 "spring-boot-feature-toggles-with-togglz"
 "spring-boot-rest-assured"
 "spring-boot-thymeleaf-testing"
 "testcontainers-introduction"
 "maven-junit-paralellize-tests"
)

for project in "${arr[@]}"
do
  mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -f $project/pom.xml verify
done
