#!/bin/bash
set -x
awslocal s3 mb s3://oder-event-bucket
awslocal sqs create-queue --queue-name order-event-queue
set +x
