# Codebase for the blog post [#HOWTO: Simple CRUD table with JSF and PrimeFaces]()

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `simple-crud-table-with-jsf-and-primefaces`
3. Build the application with `mvn clean package`
4. Start you Docker deamon
5. Build the Docker image with `docker build -t crud-table-jsf-primefaces .`
6. Start the Docker container with`docker run -p 8080:8080 crud-table-jsf-primefaces`
7. Wait until the Payara server launched successfully and visit `http://localhost:8080/crud-table-jsf-primefaces`
