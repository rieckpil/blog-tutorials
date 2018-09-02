# Codebase for the blog post [#HOWTO: Up- and download files with React and Spring Boot](https://rieckpil.de/howto-up-and-download-files-with-react-and-spring-boot/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `spring-boot-uploading-and-downloading-files-with-react`
3. Build the frontend and backend with `./buildAll.sh` or manually with (`mvn clean package` in the backend folder and `npm install && npm build` in the frontend folder)
4. Launch your Docker deamon (you need `docker-compose` for this example)
5. Start the infrastructure with `docker-compose up --build`
6. Visit `http://localhost:80` and upload some files and then download them in a random way
