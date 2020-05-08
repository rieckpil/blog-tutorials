# Codebase for the blog post [Java AWS Lambda with Serverless and Maven - Thumbnail Generator](http://rieckpil.de/aws-lambda-with-serverless-java-and-maven-thumbnail-generator)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `serverless-java-aws-examples/thumbnail-generator`
3. Build the project with Maven `mvn package`
4. Follow the required AWS and Serverless setup steps described in the blog post
5. Change the name of the S3 bucket (it has to be unique)
6. Deploy the AWS Lambda with Serverless `serverless deploy -v`
7. Upload `.png` files to S3 and wait for the created thumbnail:
```shell script
aws s3api put-object --bucket image-uploads-java-thumbnail-example --key uploads/myPicture.png --body myPicture.png --profile serverless-admin
aws s3api list-objects-v2 --bucket image-uploads-java-thumbnail-example --profile serverless-admin
```
