# Codebase for the blog post [MicroProfile Rest Client for RESTful communication](https://rieckpil.de/howto-microprofile-rest-client-for-restful-communication/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `microprofile-rest-client-for-restful-communication`
3. Navigate to the folder `order-application` and build the application with `mvn clean package`
4. Navigate to the folder `user-management-application` and build the application with `mvn clean package`
5. Start your Docker daemon (`docker-compose` is required)
6. Build the infrastructure with `docker-compose build`
7. Start everything with `docker-compose up`
8. Wait until everything is started and visit `http://localhost:8080/resources/orders/1` or create a new order with HTTP POST `http://localhost:8080/resources/orders` and the following body:
```
{
    "productName" : "Flatscreen",
    "userId": 12,
    "amount": 12,
    "price": 13.37
}
```
