#!/usr/bin/env bash
set -e
export AWS_PAGER=""

aws cloudformation create-stack \
  --stack-name cognito-test \
  --template-body file://cognito.yml \
  --capabilities CAPABILITY_IAM \
  --parameters \
    ParameterKey=ApplicationName,ParameterValue=rieckpil-test \
    ParameterKey=LoginPageDomainPrefix,ParameterValue=rieckpil-test

aws cloudformation wait stack-create-complete --stack-name cognito-test

USER_POOL_ID=$(aws cloudformation describe-stacks --stack-name cognito-test --output text --query 'Stacks[0].Outputs[?OutputKey==`UserPoolId`].OutputValue')
USER_POOL_PROVIDER_URL=$(aws cloudformation describe-stacks --stack-name cognito-test --output text --query 'Stacks[0].Outputs[?OutputKey==`UserPoolProviderUrl`].OutputValue')
USER_POOL_CLIENT_ID=$(aws cloudformation describe-stacks --stack-name cognito-test --output text --query 'Stacks[0].Outputs[?OutputKey==`UserPoolClientId`].OutputValue')
USER_POOL_CLIENT_SECRET=$(aws cognito-idp describe-user-pool-client --user-pool-id $USER_POOL_ID --client-id $USER_POOL_CLIENT_ID --output text --query 'UserPoolClient.ClientSecret')
USER_POOL_LOGOUT_URL=$(aws cloudformation describe-stacks --stack-name cognito-test --output text --query 'Stacks[0].Outputs[?OutputKey==`UserPoolLogoutUrl`].OutputValue')

echo "USER_POOL_ID: $USER_POOL_ID"
echo "USER_POOL_PROVIDER_URL: $USER_POOL_PROVIDER_URL"
echo "USER_POOL_CLIENT_ID: $USER_POOL_CLIENT_ID"
echo "USER_POOL_CLIENT_SECRET: $USER_POOL_CLIENT_SECRET"
echo "USER_POOL_LOGOUT_URL: $USER_POOL_LOGOUT_URL"
