# Codebase for the blog post [#HOWTO:  Deploy a Spring Boot Uber-Jar application within Open Liberty](https://rieckpil.de/howto-run-spring-boot-uber-jar-application-within-open-liberty/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-boot-with-open-liberty`
3. Build the project with `mvn clean package`
4. Download the latest Open Liberty (at least 18.0.0.2) from https://openliberty.io/downloads/ and extract the zip 
5. Navigate to the `wlp` folder you extracted and run `bin/server start defaultServer` or `bin/server.bat start defaultServer` for Windows
6. Open the `server.xml` file in `wlp/usr/server/defaultServer` and replace the content with the following configuration:
```
<?xml version="1.0" encoding="UTF-8"?>
<server description="new server">
    <featureManager>
        <feature>springBoot-2.0</feature>
        <feature>servlet-4.0</feature>
        <feature>websocket-1.1</feature>
    </featureManager>
    <httpEndpoint id="defaultHttpEndpoint"
                  httpPort="9080"
                  httpsPort="9443" />
    <applicationManager autoExpand="true"/>
    <springBootApplication location="spring-boot-with-open-liberty.jar"/>
</server>
```
7. Copy the already built (step 3.) `spring-boot-with-open-liberty.jar` to `wlp/usr/server/defaultServer/apps`
8. Visit `http://localhost:9080` for chatting and `http://localhost:9080/api/messages` to get all chat messages