#!/bin/bash

set -e
set -o pipefail

declare -a arr=("rest-easy-file-uploading-and-downloading" 
 "charts-in-pdf-java-ee"
 "dynamic-sql-querying-with-pagination"
 "generate-documents-from-word-templates-with-docx4j-on-wildfly14"
 "graalvm-intro"
 "hello-world-jsf-2.3"
 "embedded-messaging-engine-open-liberty"
 "improved-java-ee-productivity-with-wad"
 "java-ee-kubernetes-deployment"
 "java-ee-sending-mails"
 "java-ee-uploading-and-downloading-files-with-web-components/backend"
 "java-magazin-jsf-primefaces"
 "spring-boot-with-open-liberty"
 "jax-rs-api-rate-limiting-with-jsr-375"
 "jpa-integration-tests-java-ee"
 "jsf-simple-login-with-java-ee-security-api"
 "lazy-loading-of-jpa-attributes-with-hibernate"
 "load-testing-your-application"
 "messaging-with-jms-using-payara"
 "microprofile-jwt-keycloak-auth/backend"
 "microprofile-rest-client-for-restful-communication/order-application"
 "microprofile-rest-client-for-restful-communication/user-management-application"
 "spring-boot-uploading-and-downloading-files-with-react/backend"
 "nice-looking-jsf-apps-with-prime-faces-7"
 "intercept-methods-with-cdi-interceptors"
 "simple-crud-table-with-jsf-and-primefaces"
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