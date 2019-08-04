FROM open-liberty:kernel-java11
COPY --chown=1001:0 target/java-ee-with-kotlin.war /config/dropins/
COPY --chown=1001:0 server.xml /config/
COPY --chown=1001:0 derby.jar /config/