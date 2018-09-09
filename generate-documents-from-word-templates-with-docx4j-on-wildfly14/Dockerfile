FROM jboss/wildfly:14.0.0.Final

ENV JAVA_OPTS="-Xms64m -Xmx1024m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Dcom.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize=true"

ADD target/ROOT.war /opt/jboss/wildfly/standalone/deployments/