# Codebase for the blog post [#HOWTO: MicroProfile JWT Authentication with Keycloak and React](https://rieckpil.de/howto-microprofile-jwt-authentication-with-keycloak-and-react/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `microprofile-jwt-keycloak-auth`
3. Build the Keycloak image in `keycloak` folder (`docker build -t mykeycloak .`) and start the container with `docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=verysecure -p 8282:8080 mykeycloak`
4. Visit `http://localhost:8282/auth` and login with `admin:verysecure`
5. Go to `MicroProfile` realm -> `Users`, `Add User` and add password afterwards at `Credentials` tab
6. Copy the `MicroProfile` realm RSA public key from Keycloak admin UI to `/backend/src/main/resources/META-INF/orange.pem`
7. Start the frontend application: `cd frontend` -> `npm install` -> `npm start`
8. Build and run the backend application: `cd backend` -> `mvn clean package` -> `docker build -t backend .` -> `docker run -p 8080:8080 backend`
9. Visit `http://localhost:3000` and login with the created user