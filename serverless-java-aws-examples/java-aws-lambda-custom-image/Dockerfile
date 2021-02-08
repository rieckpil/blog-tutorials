FROM adoptopenjdk/openjdk15:ubuntu-jre

# (Optional) Install any additional package
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y wget

COPY target/dependency/* /function/
COPY target/java-aws-lambda-custom-image.jar /function

ENTRYPOINT [ "/opt/java/openjdk/bin/java", "-cp", "/function/*", "com.amazonaws.services.lambda.runtime.api.client.AWSLambda" ]
CMD ["de.rieckpil.blog.Java15Lambda::handleRequest"]
