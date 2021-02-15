#!/usr/bin/env bash
set -e
export AWS_PAGER=""

aws cloudformation delete-stack --stack-name cognito-test

aws cloudformation wait stack-delete-complete --stack-name cognito-test
