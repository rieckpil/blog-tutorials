FROM postgres:9.6

LABEL maintainer = "mail@philipriecks.de"

ENV MAVEN_OPTS -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn 

RUN apt-get update && \
    apt-get install -y \
    postgresql-server-dev-9.6 \
    gcc \
    g++ \
    openssl \
    libssl-dev \
    maven \
    git \
    libecpg-dev \
    libkrb5-dev \
    default-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/

RUN cd /tmp && \
    git clone https://github.com/tada/pljava.git && \
    cd pljava && \
    git checkout refs/tags/V1_5_2 && \
    mvn clean install -B && \
    java -jar pljava-packaging/target/pljava-pg9.6-amd64-Linux-gpp.jar

COPY INIT_JAVA_EXTENSION.sql /docker-entrypoint-initdb.d
COPY INIT_SAMPLE_DATA.sql /docker-entrypoint-initdb.d

COPY simple-java-function /tmp/simple-java-function

RUN cd /tmp/simple-java-function && \
     mvn clean package -B