# Codebase for the blog post [#HOWTO: Simple JMS application with the embedded messaging engine in Open Liberty](https://rieckpil.de/howto-simple-jms-application-with-the-embedded-messaging-engine-in-open-liberty/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `embedded-messaging-engine-open-liberty`
3. Build the application with `mvn clean package`
4. Start you Docker deamon
5. Create the docker image with `docker build -t open-liberty-jms .`
6. Start the docker image `docker run open-liberty-jms`
7. Have a look at the logs for the produced JMS messages