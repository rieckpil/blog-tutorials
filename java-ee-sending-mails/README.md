# Codebase for the blog post [Send emails with Java EE using Payara](https://rieckpil.de/howto-send-emails-with-java-ee-using-payara)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `java-ee-sending-mails`
3. Replace the targeted email in `src/main/resources/META-INF/microprofile-config.properties` with yours
4. Build the project with `mvn clean package`
5. Start your Docker daemon (`docker-compose` required)
6. Run `docker-compose build && docker-compose up`
7. Visit `http://localhost:8080/resources/mails` to send a sample email to your address (the incoming email might be blocked by your email provider or marked as spam)
