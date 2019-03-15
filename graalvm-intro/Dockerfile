FROM oracle/graalvm-ce:1.0.0-rc13

COPY src/main/java/de/rieckpil/blog/PrettyPrintJSON.java /tmp/PrettyPrintJSON.java

RUN native-image /tmp/PrettyPrintJSON.java