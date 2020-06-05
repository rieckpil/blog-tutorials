Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `serverless-java-aws-examples/simple-aws-lambda`
3. Build the project with Maven `mvn package`
4. Follow the required AWS and Serverless setup steps described in [here](http://rieckpil.de/aws-lambda-with-serverless-java-and-maven-thumbnail-generator)
5. Deploy the AWS Lambda with Serverless `serverless deploy -v`
6. Execute the Lambda with `serverless invoke -f simpleLambda -d 'Hello World!'`
