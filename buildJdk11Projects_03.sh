#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "spring-web-client-expose-metrics"
 "spring-boot-selenium-integration-tests"
 "testing-json-serialization-spring"
 "spring-web-mvc-cheat-sheet"
 "spring-boot-aws-ssm-parameter-resolving"
 "expose-git-information-actuator"
 "serverless-java-aws-examples/thumbnail-generator"
 "serverless-java-aws-examples/spring-cloud-function-aws"
 "serverless-java-aws-examples/spring-cloud-function-kotlin-aws"
 "spring-security-aws-cognito-thymeleaf"
 "testcontainers-reuse-existing-containers"
 "five-unknown-junit-5-features"
 "spring-test-context-caching-introduction"
 "spring-boot-kotlin-testcontainers"
 "dependency-version-update-plugin"
 "spring-boot-test-slice-annotations"
 "testing-spring-boot-applications-with-mockmvc"
 "mockito-tips-and-tricks"
 "write-concise-web-tests-with-selenide"
 "testing-libraries-overview"
 "spring-boot-test-spring-events"
 "spring-boot-testing-tips-and-tricks"
 "testing-java-applications-with-maven"
 "open-rewrite-example"
)

for project in "${arr[@]}"
do
  mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -f $project/pom.xml verify
done
