# Codebase for the blog post [Automatic Java Code Migration with OpenRewrite (Mockito Example)](https://rieckpil.de/automatic-java-code-migration-with-openrewrite-mockito-example)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `open-rewrite-example`
3. Perform a dry run with `./mvnw rewrite:dryRun`
4. Perform the actual migration with `./mvnw rewrite:run`
5. Update Mockito to `3.12.4`
6. Run all tests with `./mvnw test`
