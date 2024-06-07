FROM openliberty/open-liberty:20.0.0.10-kernel-java11-openj9-ubi

COPY --chown=1001:0  target/charts-in-pdf-java-ee.war /config/dropins/
COPY --chown=1001:0  server.xml /config/

RUN configure.sh
