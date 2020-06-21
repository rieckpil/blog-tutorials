# Codebase for the blog post [Reuse containers with Testcontainers for fast integration tests](https://rieckpil.de/reuse-containers-with-testcontainers-for-fast-integration-tests/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `testcontainers-reuse-existing-containers`
3. Ensure your Docker engine is running (`docker ps`)
4. Run all integration tests with `mvn verify`
5. Ensure the Docker containers for PostgreSQL are still running (one for PostgreSQl 9.6 and one for 10)
6. Re-run the integration tests. They should finish faster now
