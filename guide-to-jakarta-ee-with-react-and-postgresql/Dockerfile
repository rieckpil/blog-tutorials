FROM open-liberty:kernel-java11
COPY --chown=1001:0 postgresql-42.2.8.jar /opt/ol/wlp/lib/
COPY --chown=1001:0 target/guide-to-jakarta-ee-with-react-and-postgresql.war /config/dropins/
COPY --chown=1001:0 server.xml /config