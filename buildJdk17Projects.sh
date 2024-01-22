#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "testing-spring-rest-template"

 "serverless-java-aws-examples/java-aws-lambda-custom-image"

 "launchdarkly-java-development-testing-hints"

 "random-data-in-java-using-java-faker"

 "testcontainers-introduction"
 "maven-junit-paralellize-tests"
)

for project in "${arr[@]}"
do
  mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -f $project/pom.xml verify
done
