# Codebase for the blog post [#REVIEW: Improved Java/Jakarta EE productivity with Adam Bien’s WAD (Watch and Deploy)](https://rieckpil.de/review-improved-java-jakarta-ee-productivity-with-adam-biens-wad-watch-and-deploy/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `improved-java-ee-productivity-with-wad`
3. Make sure you have a Java EE 8 compliant application server installed on your machine
4. Start the application server of your choice
5. Create or copy the `.wadrc` to your home directory (e.g. `/Users/duke/` for Mac or `C:/Users/duke` on Windows or `/home/duke/` for Linux) and specify your deployment folders separated by a column in this file like:

```
C:\Development\Server\Payara\payara-5.183\glassfish\domains\domain1\autodeploy
C:\Development\Server\OpenLiberty\openliberty-19.0.0.1\usr\servers\defaultServer\dropins
C:\Development\Server\Wildfly\wildfly-16.0.0\standalone\deployments
C:\Development\Server\TomEE\apache-tomee-plume-8.0.0-M2\webapps
```

6. Run `java -jar wad.jar` and wait until the `.war` file is built and copied to your folders:

```
$ java -jar wad.jar
wad 0.1.1-SNAPSHOT
 'C:\Development\Server\TomEE\apache-tomee-plume-8.0.0-M2\webapps'  from ~/.wadrc
 'C:\Development\Server\Wildfly\wildfly-16.0.0\standalone\deployments'  from ~/.wadrc
 'C:\Development\Server\OpenLiberty\openliberty-19.0.0.1\usr\servers\defaultServer\dropins'  from ~/.wadrc
 'C:\Development\Server\Payara\payara-5.183\glassfish\domains\domain1\autodeploy'  from ~/.wadrc
resulting deployment folders are:
 'C:\Development\Server\TomEE\apache-tomee-plume-8.0.0-M2\webapps'
 'C:\Development\Server\Wildfly\wildfly-16.0.0\standalone\deployments'
 'C:\Development\Server\OpenLiberty\openliberty-19.0.0.1\usr\servers\defaultServer\dropins'
 'C:\Development\Server\Payara\payara-5.183\glassfish\domains\domain1\autodeploy'
WAD is watching .\src\main, deploying target\improved-java-ee-productivity-with-wad.war to [C:\Development\Server\TomEE\apache-tomee-plume-8.0.0-M2\webapps\improved-java-ee-productivity-with-wad.war, C:\Development\Server\Wildfly\wildfly-16.0.0\standalone\deployments\improved-java-ee-productivity-with-wad.war, C:\Development\Server\OpenLiberty\openliberty-19.0.0.1\usr\servers\defaultServer\dropins\improved-java-ee-productivity-with-wad.war, C:\Development\Server\Payara\payara-5.183\glassfish\domains\domain1\autodeploy\improved-java-ee-productivity-with-wad.war]
[11:01:37][1] built in 2449 ms
Copying 4kB ThinWAR to  (...)proved-java-ee-productivity-with-wad.war
Copying 4kB ThinWAR to  (...)proved-java-ee-productivity-with-wad.war
Copying 4kB ThinWAR to  (...)proved-java-ee-productivity-with-wad.war
Copying 4kB ThinWAR to  (...)proved-java-ee-productivity-with-wad.war
copied in 9 ms

```

7. Make changes to the Java EE project and see how it immediately gets built and deployed