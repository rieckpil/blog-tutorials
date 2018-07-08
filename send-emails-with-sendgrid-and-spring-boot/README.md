# Codebase for the blog post [#HOWTO:  Send E-Mails with SendGrid and Spring Boot](https://rieckpil.de/howto-send-e-mails-with-sendgrid-and-spring-boot/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `send-emails-with-sendgrid-and-spring-boot`
3. Open `application.properties` file in `/src/main/resources` and replace the values with you **API key** and your template id
4. Open the `MailController` class and replace the email addresses
5. Build the project with `mvn clean package`
6. Run this project with `mvn spring-boot:run` 
7. Visit `http://localhost:8080/sendgrid?msg=HelloWorld` to send an email
