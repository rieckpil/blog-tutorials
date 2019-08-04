# Codebase for the blog post [#HOWTO: Write Java EE applications with Kotlin](https://rieckpil.de/howto-write-java-ee-applications-with-kotlin/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `java-ee-with-kotlin`
3. Start your Docker daemon
4. Execute `buildAndRun.bat` (for Windows) and `buildAndRun.sh` (for Linux/Mac)
5. Wait until Open Liberty is read to access requests: `docker logs -f java-ee-with-kotlin`
6. Hit the following endpoints with (HTTP GET):

```bash
http://localhost:9080/resources/books
http://localhost:9080/resources/books/1
http://localhost:9080/resources/books/42
```
