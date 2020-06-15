FROM payara/server-full:5.201
COPY create-mail-session.asadmin $CONFIG_DIR
COPY target/java-ee-sending-mails.war $DEPLOY_DIR
ENV POSTBOOT_COMMANDS $CONFIG_DIR/create-mail-session.asadmin
