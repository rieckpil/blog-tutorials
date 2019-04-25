# Codebase for the blog post [#HOWTO: Messaging with JMS using Payara with embedded OpenMQ broker](https://rieckpil.de/howto-messaging-with-jms-using-payara-with-embedded-openmq-broker)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `messaging-with-jms-using-payara`
3. Start your Docker daemon
4. Build the project with `mvn clean package`
5. Build the Docker image with `docker build -t payarajms`
6. Start the Docker image (not detached, to see the log output): `docker run -p 8080:8080 -p 4848:4848 payarajms`