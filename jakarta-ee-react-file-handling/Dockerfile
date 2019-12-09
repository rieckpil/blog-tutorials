FROM open-liberty:kernel-java11
COPY --chown=1001:0  target/jakarta-ee-react-file-handling.war /config/dropins/
COPY --chown=1001:0  src/main/liberty/config/server.xml /config
