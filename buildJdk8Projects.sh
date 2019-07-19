#!/bin/bash
declare -a arr=("rest-easy-file-uploading-and-downloading" 
 "spring-boot-with-open-liberty"
 "send-emails-with-sendgrid-and-spring-boot")

for project in "${arr[@]}"
do
   mvn -B -f $project/pom.xml package
done