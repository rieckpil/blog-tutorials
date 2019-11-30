FROM open-liberty:kernel-java11
COPY --chown=1001:0 target/open-maven-plugin-review.war /config/dropins/
COPY --chown=1001:0 src/main/liberty/config/server.xml /config
COPY --chown=1001:0 src/main/liberty/config/h2.jar /config
