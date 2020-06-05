# Codebase for the blog post [Simple load-testing with Apache Benchmark](https://rieckpil.de/howto-simple-load-testing-with-apache-benchmark/)

Steps to run this project:

1. Clone this Git repository
2. Make sure you have `ab` available at your command line (part of [Apache HTTPD](https://httpd.apache.org/))
3. Navigate to the folder `load-testing-your-application`
4. Launch the Spring Boot application with `mvn spring-boot:run`
5. (Optinal) Open Java VisualVM
6. Run `ab -n 5000 -c 15 http://localhost:8080/persons` and wait for the result/see within Java VisualVM how the application behaves
