# Codebase for the blog post []()

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `java-ee-api-rate-limiting-with-jsr-375`
3. Build the application with `mvn clean package`
4. Start you Docker deamon
5. Build the docker image with `docker build -t api-rate-limiting .`
6. Start a docker container with `docker run -p 8080:8080 -d api-rate-limiting`
7. For Linux/macOS: Make the `reachApi.sh` file executable with `chmod +x reachApi.sh`
8. Execute the script to make some API calls with `./reachApi.sh`
9. For Windows: Use e.g. Postman or your browser and make some API calls to 
`http://localhost:8080/api-rate-limiting/resources/stocks` with the username `rieckpil` and password `HelloWorld`
10. The eleventh API call within one minute should result in a HTTP `429 - Too Many Requests` response
11. Wait one minute and you should be able to call the API again and get a valid result