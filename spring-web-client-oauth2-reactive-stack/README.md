# Codebase for the blog post [#HOWTO: Spring WebClient OAuth2 Integration for Spring WebFlux](https://rieckpil.de/spring-webclient-oauth2-integration-using-github-as-an-example/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-web-client-oauth2-reactive-stack`
3. Register a new GitHub OAuth2 application (as described [here](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)) and copy the `client-id` and `client-secret` to the application.yml file 
4. Start the application (either from your IDE or with `mvn spring-boot:run`)
5. Visit http://localhost:8080 and log in with your GitHub account
6. You should now see a list of all your GitHub repositories (max. 100)