#!/bin/bash

set -e
set -o pipefail

declare -a arr=(
 "spring-boot-datajpatest"
)

for project in "${arr[@]}"
do
  mvn -B -f $project/pom.xml verify
done
