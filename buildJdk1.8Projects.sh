#!/bin/bash

set -e
set -o pipefail

declare -a arr=("rest-easy-file-uploading-and-downloading"
 "generate-documents-from-word-templates-with-docx4j-on-wildfly14"
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
 "load-testing-your-application"
 "messaging-with-jms-using-payara"
 "microprofile-jwt-keycloak-auth/backend"
 "microprofile-rest-client-for-restful-communication/order-application"
 "microprofile-rest-client-for-restful-communication/user-management-application"
 "nice-looking-jsf-apps-with-prime-faces-7"
 "intercept-methods-with-cdi-interceptors"
 "simple-crud-table-with-jsf-and-primefaces")

for project in "${arr[@]}"
do
  mvn -B -f $project/pom.xml verify
done
