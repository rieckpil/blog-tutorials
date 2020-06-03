# Codebase for the blog post [Java AWS Lambda with Spring Cloud Function](https://rieckpil.de/java-aws-lambda-with-spring-cloud-function/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `serverless-java-aws-examples/spring-cloud-function-aws`
3. Ensure you have the Serverless Framework available on your machine (install with `npm install -g serverless`)
4. Configure access to AWS for Serverless (find more on this topic [here](https://rieckpil.de/aws-lambda-with-serverless-java-and-maven-thumbnail-generator/))
5. Build the project with `mvn package`
6. Rename the AWS S3 bucket inside `serverless.yml` (`custom.orderEventBucket`) to something else as the S3 bucket name has to be unique
7. Deploy everything to AWS with `serverless deploy`
8. Start using the functions like explained in the blog post

