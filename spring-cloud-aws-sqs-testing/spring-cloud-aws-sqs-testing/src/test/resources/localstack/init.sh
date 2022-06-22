#!/bin/sh

awslocal sqs create-queue --queue-name test-order-queue

echo "Initialized."
