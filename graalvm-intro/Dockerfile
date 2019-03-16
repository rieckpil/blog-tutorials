FROM oracle/graalvm-ce:1.0.0-rc13

COPY src/main/java/*.java /tmp/
COPY prettyMe.json /tmp/

# compile the Java source code to byte code
RUN javac /tmp/HelloWorld.java
RUN javac /tmp/PrettyPrintJSON.java

# create native images
WORKDIR /tmp
RUN native-image HelloWorld
RUN native-image --language:js PrettyPrintJSON

RUN ./helloworld
RUN ./prettyprintjson < /tmp/prettyMe.json