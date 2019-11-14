# Codebase for the blog post [#HOWTO: Create real-time applications with Jakarta EE WebSocket](https://rieckpil.de/create-real-time-applications-with-jakarta-ee-websocket/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `websockets-with-jakarta-ee`
3. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
4. Wait until Open Liberty is up- and running (e.g. use `docker logs -f CONTAINER_ID`)
5. Visit http://localhost:9080/ and wait for the incoming stock informations (every five seconds) or send your own message
6. Have a look at the Docker container logs (e.g. use `docker logs -f CONTAINER_ID`) to see the console output of the Java WebSocket client
