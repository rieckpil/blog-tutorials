# Codebase for the blog post [#HOWTO: Up- and download files with Java EE and Web Components](https://rieckpil.de/howto-up-and-download-files-with-java-ee-and-web-components)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `java-ee-uploading-and-downloading-files-with-web-components`
3. Build the backend with `cd backend && mvn clean package -DskipTests`
4. Launch your Docker deamon (you need `docker-compose` for this example)
5. Start the infrastructure with `docker-compose up --build --force-recreate`
6. Visit `http://localhost:80` and upload some files and then download them in a random way