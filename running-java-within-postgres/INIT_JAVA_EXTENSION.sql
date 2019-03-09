SET pljava.libjvm_location TO '/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/amd64/server/libjvm.so';
CREATE EXTENSION pljava;
GRANT USAGE ON LANGUAGE java TO postgres;
ALTER DATABASE postgres SET pljava.libjvm_location FROM CURRENT;

SELECT sqlj.install_jar( 'file:///tmp/simple-java-function/target/simple-java-function.jar','jfunctions', true );
SELECT sqlj.set_classpath( 'public', 'jfunctions' );