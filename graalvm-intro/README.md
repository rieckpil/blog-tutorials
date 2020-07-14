# Codebase for the blog post [GraalVM – an introduction to the next level JVM](https://rieckpil.de/whatis-graalvm/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `graalvm-intro`
3. Start your docker engine
4. Build the image with `docker build -t graalvmintro .` and see the native image creation and execution during build
5. Optional update `src/main/java` for a new Java class of your choice and add it to the `Dockerfile`
