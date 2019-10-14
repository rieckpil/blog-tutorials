# Codebase for the blog post [#HOWTO: Jakarta EE and MicroProfile applications with React and PostgreSQL](https://rieckpil.de/jakarta-ee-and-microprofile-applications-with-react-and-postgresql)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `guide-to-jakarta-ee-with-react-and-postgresql`
3. Navigate to the `frontend` folder to install all dependencies with `npm install` and build the app with `npm run build`
4. Navigate back to the root of this project and execute the following:
```shell script
mvn clean package
docker-compose build
docker-compose up --force-recreate
```
5. Wait until everything is up- and running and then visit: http://localhost:8080
