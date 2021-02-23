#!/bin/sh

awslocal sqs create-queue --queue-name testcontainers
awslocal s3api create-bucket --bucket testcontainers

echo "Initialized."
