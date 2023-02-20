#!/bin/bash

set -e
set -o pipefail

declare -a arr=("kotlin-javascript-transpiling-gradle")

for project in "${arr[@]}"
do
  cd $project
  ./gradlew build
  cd ..
done