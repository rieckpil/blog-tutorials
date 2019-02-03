# Codebase for the blog post [#HOWTO: Simple form-based authentication for JSF 2.3 with Java EE 8 Security API](https://rieckpil.de/howto-simple-form-based-authentication-for-jsf-2-3-with-java-ee-8-security-api/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `jsf-simple-login-with-java-ee-security-api`
3. Build the application with `mvn clean package`
4. Start you Docker deamon
5. Build the Docker image with `docker build -t jsf-simple-login .`
6. Star the Docker container with `docker run -p 8080:8080 jsf-simple-login`
7. Wait until the Payara server launched successfully and visit `http://localhost:8080/jsf-simple-login`
8. Try to authenticate with on of the two users (mail: `admin@mail.com` password: `ADMIN1234` or mail: `user@mail.com` 
password: `USER1234`)

## Hint for running this example on WildFly 14+

You need the following `jboss-web.xml` in `src/main/webapp/WEB-INF`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<jboss-web version="8.0" xmlns="http://www.jboss.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.jboss.com/xml/ns/javaee http://www.jboss.org/schema/jbossas/jboss-web_8_0.xsd">
  <context-root/>
  <security-domain>jaspitest</security-domain>
</jboss-web>
```