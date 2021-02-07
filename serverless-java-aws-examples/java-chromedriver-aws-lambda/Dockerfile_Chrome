FROM public.ecr.aws/lambda/java:11

RUN yum install -y wget unzip libX11

RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm && \
    yum install -y google-chrome-stable_current_x86_64.rpm

RUN CHROME_DRIVER_VERSION=`curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE` && \
    wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/

COPY target/dependency ${LAMBDA_TASK_ROOT}/lib/
COPY target/classes ${LAMBDA_TASK_ROOT}

CMD ["de.rieckpil.blog.InvokeWebDriver::handleRequest"]
