FROM open-liberty:kernel-java11

COPY --chown=1001:0  src/main/liberty/config/postgres /config/postgres
COPY --chown=1001:0  src/main/liberty/config/server.xml /config
RUN configure.sh
COPY --chown=1001:0  target/review-microshed-testing.war /config/dropins/
