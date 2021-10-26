# Codebase for the blog post [AWS Lambda with Kotlin and Spring Cloud Function](https://rieckpil.de/aws-lambda-with-kotlin-and-spring-cloud-function/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `serverless-java-aws-examples/spring-cloud-function-kotlin-aws`
3. Make sure you've installed & set up the Serverless CLI
4. Run `serverless deploy --aws-profile your-profile`
5. Invoke the function with `sls invoke -f fetch-random-quotes --aws-profile your-profile`
