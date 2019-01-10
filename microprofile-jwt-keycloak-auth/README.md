# Codebase for the blog post [#HOWTO: MicroProfile JWT Authentication with Keycloak and React](https://rieckpil.de/howto-microprofile-jwt-authentication-with-keycloak-and-react/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `microprofile-jwt-keycloak-auth`
3. Build the Keycloak image in `keycloak` folder (`docker build -t mykeycloak .`) and start the container with `docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=verysecure -p 8282:8080 mykeycloak`
4. Visit `http://localhost:8282/auth` and login with `admin:verysecure`
5. Go to `MicroProfile` realm -> `Users`, `Add User` and add password afterwards at `Credentials` tab. Add this user to the `USER` group within the `Groups` tab of the user settings.
6. Copy the `MicroProfile` realm RSA public key (go to `Realm Settings` -> `Keys` -> `Public key` button of the RSA256 key) from Keycloak admin UI to `/backend/src/main/resources/META-INF/orange.pem`
7. Start the frontend application: `cd frontend` -> `npm install` -> `npm start`
8. Build and run the backend application: `cd backend` -> `buildAndRun.bat` or `buildAndRun.sh`
9. Visit `http://localhost:3000` and login with the created user
10. Hit the `Access REST API` button and try to get the secured message