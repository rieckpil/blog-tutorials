FROM openliberty/open-liberty:20.0.0.5-kernel-java11-openj9-ubi

COPY --chown=1001:0  src/main/liberty/config/postgres /config/postgres
COPY --chown=1001:0  src/main/liberty/config/server.xml /config
COPY --chown=1001:0  target/review-microshed-testing.war /config/dropins/

RUN configure.sh
