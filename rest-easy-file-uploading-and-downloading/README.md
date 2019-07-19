# Codebase for the blog post [#HOWTO: RESTEasy (WildFly) file up- and downloading](https://rieckpil.de/howto-resteasy-wildfly-jax-rs-2-1-file-up-and-downloading/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `rest-easy-file-uploading-and-downloading`
3. Start your Docker daemon
4. Run the `buildAndRun.bat` (Windows) or `./buildAndRun.sh` (Mac/Linux - run `chmod +x buildAndRun.sh` if you can't execute it) script
5. Visit http://localhost:8080 and upload a file of your choice
6. Visit http://localhost:8080/resources/files to download a random file
