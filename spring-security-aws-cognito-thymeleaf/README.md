# Codebase for the blog post [Thymeleaf OAuth2 Login with Spring Security and AWS Cognito](https://rieckpil.de/thymeleaf-oauth2-login-with-spring-security-and-aws-cognito/) and [OIDC Logout With AWS Cognito and Spring Security](https://rieckpil.de/oidc-logout-with-aws-cognito-and-spring-security/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-security-aws-cognito-thymeleaf`
3. Follow the required AWS Cognito setup steps as described in the [tutorial](https://rieckpil.de/thymeleaf-oauth2-login-with-spring-security-and-aws-cognito/) (or use the CloudFormation template inside the `cloudformation` folder)
4. Start the application with `mvn spring-boot:run`
5. Visit http://localhost:8080/ and login
