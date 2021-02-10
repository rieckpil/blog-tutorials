# Codebase for the blog post [Java AWS Lambda Container Image Support (Complete Guide)](https://rieckpil.de/java-aws-lambda-container-image-support-complete-guide/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `serverless-java-aws-examples/java-aws-lambda-custom-image`
3. Build the Docker image with:

```shell script
mvn package
docker build -t . java-aws-lambda-custom-image
```

4. Push the Docker image to your ECR (follow the steps described in the blog post - make sure to replace your AWS Account ID and region)
5. Deploy the function with `serverless deploy`
