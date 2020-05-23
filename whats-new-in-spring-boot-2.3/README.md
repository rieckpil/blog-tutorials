# Codebase for the blog post [What's new in Spring Boot 2.3](https://rieckpil.de/whats-new-in-spring-boot-2-3/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `whats-new-in-spring-boot-2-3`
3. Ensure you have Java 14 installed `java -version`
4. Build the application with `mvn package`
5. Start the Docker container:
```
docker run -p 8080:8080 -e JAVA_OPTS='--enable-preview' myregistry.com/rieckpil/whats-new-in-spring-boot-2-3:latest
```

... and access e.g. http://localhost:8080/users

Contents of the layered `.jar` file:
```
jar -tf target/whats-new-in-spring-boot-2-3.jar
META-INF/
META-INF/MANIFEST.MF
org/
org/springframework/
org/springframework/boot/
org/springframework/boot/loader/
org/springframework/boot/loader/ClassPathIndexFile.class
org/springframework/boot/loader/ExecutableArchiveLauncher.class
org/springframework/boot/loader/JarLauncher.class
org/springframework/boot/loader/LaunchedURLClassLoader$UseFastConnectionExceptionsEnumeration.class
org/springframework/boot/loader/LaunchedURLClassLoader.class
org/springframework/boot/loader/Launcher.class
org/springframework/boot/loader/MainMethodRunner.class
org/springframework/boot/loader/PropertiesLauncher$1.class
org/springframework/boot/loader/PropertiesLauncher$ArchiveEntryFilter.class
org/springframework/boot/loader/PropertiesLauncher$PrefixMatchingArchiveFilter.class
org/springframework/boot/loader/PropertiesLauncher.class
org/springframework/boot/loader/WarLauncher.class
org/springframework/boot/loader/archive/
org/springframework/boot/loader/archive/Archive$Entry.class
org/springframework/boot/loader/archive/Archive$EntryFilter.class
org/springframework/boot/loader/archive/Archive.class
org/springframework/boot/loader/archive/ExplodedArchive$AbstractIterator.class
org/springframework/boot/loader/archive/ExplodedArchive$ArchiveIterator.class
org/springframework/boot/loader/archive/ExplodedArchive$EntryIterator.class
org/springframework/boot/loader/archive/ExplodedArchive$FileEntry.class
org/springframework/boot/loader/archive/ExplodedArchive$SimpleJarFileArchive.class
org/springframework/boot/loader/archive/ExplodedArchive.class
org/springframework/boot/loader/archive/JarFileArchive$AbstractIterator.class
org/springframework/boot/loader/archive/JarFileArchive$EntryIterator.class
org/springframework/boot/loader/archive/JarFileArchive$JarFileEntry.class
org/springframework/boot/loader/archive/JarFileArchive$NestedArchiveIterator.class
org/springframework/boot/loader/archive/JarFileArchive.class
org/springframework/boot/loader/data/
org/springframework/boot/loader/data/RandomAccessData.class
org/springframework/boot/loader/data/RandomAccessDataFile$1.class
org/springframework/boot/loader/data/RandomAccessDataFile$DataInputStream.class
org/springframework/boot/loader/data/RandomAccessDataFile$FileAccess.class
org/springframework/boot/loader/data/RandomAccessDataFile.class
org/springframework/boot/loader/jar/
org/springframework/boot/loader/jar/AsciiBytes.class
org/springframework/boot/loader/jar/Bytes.class
org/springframework/boot/loader/jar/CentralDirectoryEndRecord$1.class
org/springframework/boot/loader/jar/CentralDirectoryEndRecord$Zip64End.class
org/springframework/boot/loader/jar/CentralDirectoryEndRecord$Zip64Locator.class
org/springframework/boot/loader/jar/CentralDirectoryEndRecord.class
org/springframework/boot/loader/jar/CentralDirectoryFileHeader.class
org/springframework/boot/loader/jar/CentralDirectoryParser.class
org/springframework/boot/loader/jar/CentralDirectoryVisitor.class
org/springframework/boot/loader/jar/FileHeader.class
org/springframework/boot/loader/jar/Handler.class
org/springframework/boot/loader/jar/JarEntry.class
org/springframework/boot/loader/jar/JarEntryFilter.class
org/springframework/boot/loader/jar/JarFile$1.class
org/springframework/boot/loader/jar/JarFile$JarEntryEnumeration.class
org/springframework/boot/loader/jar/JarFile$JarFileType.class
org/springframework/boot/loader/jar/JarFile.class
org/springframework/boot/loader/jar/JarFileEntries$1.class
org/springframework/boot/loader/jar/JarFileEntries$EntryIterator.class
org/springframework/boot/loader/jar/JarFileEntries.class
org/springframework/boot/loader/jar/JarURLConnection$1.class
org/springframework/boot/loader/jar/JarURLConnection$JarEntryName.class
org/springframework/boot/loader/jar/JarURLConnection.class
org/springframework/boot/loader/jar/StringSequence.class
org/springframework/boot/loader/jar/ZipInflaterInputStream.class
org/springframework/boot/loader/jarmode/
org/springframework/boot/loader/jarmode/JarMode.class
org/springframework/boot/loader/jarmode/JarModeLauncher.class
org/springframework/boot/loader/jarmode/TestJarMode.class
org/springframework/boot/loader/util/
org/springframework/boot/loader/util/SystemPropertyUtils.class
BOOT-INF/
BOOT-INF/classes/
BOOT-INF/classes/de/
BOOT-INF/classes/de/rieckpil/
BOOT-INF/classes/de/rieckpil/blog/
META-INF/maven/
META-INF/maven/de.rieckpil.blog/
META-INF/maven/de.rieckpil.blog/whats-new-in-spring-boot-2-3/
BOOT-INF/classes/de/rieckpil/blog/SampleController.class
BOOT-INF/classes/de/rieckpil/blog/WhatsNewInSpringBoot23Application.class
BOOT-INF/classes/de/rieckpil/blog/User.class
BOOT-INF/classes/application.properties
META-INF/maven/de.rieckpil.blog/whats-new-in-spring-boot-2-3/pom.xml
META-INF/maven/de.rieckpil.blog/whats-new-in-spring-boot-2-3/pom.properties
BOOT-INF/
BOOT-INF/lib/
BOOT-INF/lib/spring-boot-starter-validation-2.3.0.RELEASE.jar
BOOT-INF/lib/spring-boot-starter-2.3.0.RELEASE.jar
BOOT-INF/lib/spring-boot-autoconfigure-2.3.0.RELEASE.jar
BOOT-INF/lib/spring-boot-starter-logging-2.3.0.RELEASE.jar
BOOT-INF/lib/logback-classic-1.2.3.jar
BOOT-INF/lib/logback-core-1.2.3.jar
BOOT-INF/lib/log4j-to-slf4j-2.13.2.jar
BOOT-INF/lib/log4j-api-2.13.2.jar
BOOT-INF/lib/jul-to-slf4j-1.7.30.jar
BOOT-INF/lib/jakarta.annotation-api-1.3.5.jar
BOOT-INF/lib/snakeyaml-1.26.jar
BOOT-INF/lib/jakarta.el-3.0.3.jar
BOOT-INF/lib/hibernate-validator-6.1.5.Final.jar
BOOT-INF/lib/jakarta.validation-api-2.0.2.jar
BOOT-INF/lib/jboss-logging-3.4.1.Final.jar
BOOT-INF/lib/classmate-1.5.1.jar
BOOT-INF/lib/spring-boot-starter-web-2.3.0.RELEASE.jar
BOOT-INF/lib/spring-boot-starter-json-2.3.0.RELEASE.jar
BOOT-INF/lib/jackson-databind-2.11.0.jar
BOOT-INF/lib/jackson-annotations-2.11.0.jar
BOOT-INF/lib/jackson-core-2.11.0.jar
BOOT-INF/lib/jackson-datatype-jdk8-2.11.0.jar
BOOT-INF/lib/jackson-datatype-jsr310-2.11.0.jar
BOOT-INF/lib/jackson-module-parameter-names-2.11.0.jar
BOOT-INF/lib/spring-boot-starter-tomcat-2.3.0.RELEASE.jar
BOOT-INF/lib/tomcat-embed-core-9.0.35.jar
BOOT-INF/lib/tomcat-embed-websocket-9.0.35.jar
BOOT-INF/lib/spring-web-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-beans-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-webmvc-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-aop-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-context-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-expression-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-boot-properties-migrator-2.3.0.RELEASE.jar
BOOT-INF/lib/spring-boot-2.3.0.RELEASE.jar
BOOT-INF/lib/spring-boot-configuration-metadata-2.3.0.RELEASE.jar
BOOT-INF/lib/android-json-0.0.20131108.vaadin1.jar
BOOT-INF/lib/slf4j-api-1.7.30.jar
BOOT-INF/lib/spring-core-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-jcl-5.2.6.RELEASE.jar
BOOT-INF/lib/spring-boot-jarmode-layertools-2.3.0.RELEASE.jar
BOOT-INF/classpath.idx
BOOT-INF/layers.idx
```

Insights to `layers.idx`:
```
jar xf target/whats-new-in-spring-boot-2-3.jar BOOT-INF/layers.idx
cat BOOT-INF/layers.idx
- "dependencies":
  - "BOOT-INF/lib/"
- "spring-boot-loader":
  - "org/"
- "snapshot-dependencies":
- "application":
  - "BOOT-INF/classes/"
  - "BOOT-INF/classpath.idx"
  - "BOOT-INF/layers.idx"
  - "META-INF/"

```
