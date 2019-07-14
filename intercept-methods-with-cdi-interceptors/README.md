# Codebase for the blog post [#HOWTO: Intercept method calls using CDI interceptors](https://rieckpil.de/howto-intercept-method-calls-using-cdi-interceptors/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `intercept-methods-with-cdi-interceptors`
3. Start your Docker daemon
4. Execute `buildAndRun.bat` (Windows) or `buildAndRun.sh` (`chmod +X ./buildAndRun.sh` required, Linux & Mac)
5. Wait until the container is up and running
6. Access http://localhost:8080/resources/payments/mike in your browser and you should get HTTP 400
7. Access http://localhost:8080/resources/payments/mike with HTTP header `X-Secure-Payment` (either use Postman or curl) and you should get HTTP 200
8. Access http://localhost:8080/resources/payments/duke with HTTP header `X-Secure-Payment` (either use Postman or cURL) and you should get HTTP 200 and you should see the manipulated amount in the logs

```shell
curl -XGET http://localhost:8080/resources/payments/mike
curl -XGET --header "X-Secure-Payment: true" http://localhost:8080/resources/payments/mike
curl -XGET --header "X-Secure-Payment: true" http://localhost:8080/resources/payments/duke
```