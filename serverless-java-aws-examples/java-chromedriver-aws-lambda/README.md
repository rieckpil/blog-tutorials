# Commands

## Run custom image without RIE inside the base image

```shell script
docker run -v ~/.aws-lambda-rie:/aws-lambda -p 9000:8080 \
    --entrypoint /aws-lambda/aws-lambda-rie \
    java-lambda-chrome:15 \
    /usr/local/openjdk-11/bin/java -cp '/function/*' com.amazonaws.services.lambda.runtime.api.client.AWSLambda de.rieckpil.blog.InvokeWebDriver
```
