#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "spring-boot-datajpatest"
 "serverless-java-aws-examples/java-aws-lambda-custom-image"
)

for project in "${arr[@]}"
do
  mvn -B -f $project/pom.xml verify
done
