FROM open-liberty:kernel-java11

#ENV POSTGRES_PASSWORD duke
#ENV POSTGRES_PORT 5432
#ENV POSTGRES_USERNAME duke
#ENV POSTGRES_HOST localhost

COPY --chown=1001:0  target/review-microshed-testing.war /config/dropins/
COPY --chown=1001:0  postgresql-42.2.8.jar /opt/ol/wlp/lib/
COPY --chown=1001:0  server.xml /config