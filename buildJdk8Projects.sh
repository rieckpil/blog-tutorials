#!/bin/bash
declare -a arr=("rest-easy-file-uploading-and-downloading" 
 "charts-in-pdf-java-ee"
 "dynamic-sql-querying-with-pagination"
 "generate-documents-from-word-templates-with-docx4j-on-wildfly14"
 "graalvm-intro"
 "hello-world-jsf-2.3"
 "improved-java-ee-productivity-with-wad"
 "spring-boot-with-open-liberty"
 "intercept-methods-with-cdi-interceptors"
 "testcontainers"
 "java-benchmarking-with-jmh"
 "expose-git-information-actuator"
 "avoid-repeating-attributes-in-jpa-entities"
 "send-emails-with-sendgrid-and-spring-boot")

for project in "${arr[@]}"
do
  mvn -B -f $project/pom.xml verify
  if [[ "$?" -ne 0 ]] ; then
    echo "Failed to build project: $project"; exit $rc
  fi
done