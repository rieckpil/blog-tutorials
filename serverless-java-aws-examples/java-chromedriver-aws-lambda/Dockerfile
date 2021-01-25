FROM amazon/aws-lambda-java:11 as base

RUN yum install -y wget unzip libX11

RUN CHROME_DRIVER_VERSION=`curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE` && \
    wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/

FROM base as build
RUN yum install -y maven

WORKDIR /src
ADD . .

RUN mvn dependency:go-offline dependency:copy-dependencies
RUN mvn package

FROM base
WORKDIR /function

COPY --from=build /src/target/dependency/*.jar ./
COPY --from=build /src/target/*.jar ./

ENTRYPOINT [ "/usr/bin/java", "-cp", "./*", "com.amazonaws.services.lambda.runtime.api.client.AWSLambda" ]
CMD [ "de.rieckpil.blog.InvokeChrome::handleRequest" ]
