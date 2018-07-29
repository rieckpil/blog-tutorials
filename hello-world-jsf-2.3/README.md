# Codebase for the blog post [#WHATIS? JavaServer Faces. An introduction to JSF 2.3](https://rieckpil.de/whatis-java-server-faces-an-introduction-to-jsf-2-3/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `hello-world-jsf-2.3`
3. Build the application with `mvn clean package`
4. Start you Docker deamon
5. Build the Docker image with `docker build -t hello-world-jsf .`
6. Start the app with `docker run -p 8080:8080 hello-world-jsf` and wait till the application is deployed to Payara
7. Visit `http://localhost:8080/hello-world-jsf` in your browser