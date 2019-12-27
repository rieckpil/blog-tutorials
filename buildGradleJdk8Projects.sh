#!/bin/bash

set -e
set -o pipefail

declare -a arr=("kotlin-javascript-transpiling-gradle")

for project in "${arr[@]}"
do
  gradle -b $project/build.gradle.kts build
done