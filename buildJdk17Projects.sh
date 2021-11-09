#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "testing-spring-rest-template"
 "spring-boot-datajpatest"
 "serverless-java-aws-examples/java-aws-lambda-custom-image"
)

for project in "${arr[@]}"
do
  mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -f $project/pom.xml verify
done
