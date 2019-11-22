clean# Codebase for the blog post [#HOWTO: Jakarta EE integration tests with MicroShed Testing](https://rieckpil.de/jakarta-ee-integration-tests-with-microshed-testing/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `review-microshed-testing`
3. Start your Docker daemon
4. Create the `.war` file first (required for creating the Docker container for your app) with:
```
mvn package -DskipTests
```
5. Excute the integration tests with (the first execution might take some time as some Docker images are downloaded)
```
mvn test
```