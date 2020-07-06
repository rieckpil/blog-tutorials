# Codebase for the blog post [#HOWTO: Generate documents from Word templates with Docx4j on Wildfly 14](https://rieckpil.de/howto-generate-documents-from-word-templates-with-docx4j-on-wildfly-14/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `generate-documents-from-word-templates-with-docx4j-on-wildfly14`
3. Build the application with `mvn clean package`
4. Start you Docker deamon
5. Build the Docker image with `docker build -t word-generation .`
6. Start the app with `docker run -p 8080:8080 word-generation` and wait till the application is deployed to Wildfly
7. Send a HTTP POST to `http://localhost:8080/resources/messages` with the following data
```
{
	"lastName": "John",
	"firstName": "Duke",
	"message": "Hello World!",
	"salutation": "Mr."
}
```
and store the result as a `.docx` file. Or use the following cURL command: `curl -XPOST -o result.docx -H 'Content-Type: application/json' -d '{"lastName": "Duke", "firstName": "Tom", "salutation" : "Mr.", "message": "Hello World"}' http://localhost:8080/resources/messages`
