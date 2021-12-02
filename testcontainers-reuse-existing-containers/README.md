# Codebase for the blog post [Reuse containers with Testcontainers for fast integration tests](https://rieckpil.de/reuse-containers-with-testcontainers-for-fast-integration-tests/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `testcontainers-reuse-existing-containers`
3. Ensure your Docker engine is running (`docker ps`)
4. Opt-in for the reuse feature inside your `~/.testcontainers.properties` file as explained in the blog psot
5. Run all integration tests with `mvn verify`
6. Ensure the Docker containers for PostgreSQL are still running (one for PostgreSQl 13 and one for 10) with `docker ps`
7. Re-run the integration tests. They should finish faster now
