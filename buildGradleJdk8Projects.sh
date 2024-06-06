#!/bin/bash

set -e
set -o pipefail

declare -a arr=("")

for project in "${arr[@]}"
do
  cd $project
  
  cd ..
done