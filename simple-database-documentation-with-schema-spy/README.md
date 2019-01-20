# Codebase for the blog post [#HOWTO: Simple database documentation with SchemaSpy](https://rieckpil.de/howto-simple-database-documentation-with-schemaspy/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `simple-database-documentation-with-schema-spy`
3. Make sure you have installed [Graphviz](https://graphviz.org/) on your machine (optional - but required for drawing the relationship diagrams)
4. Start you Docker deamon
5. Build the Docker image with `docker build -t mypostgres .`
6. Start the Docker container with`docker run -p 5432:5432 -d mypostgres`
7. Start the database documentation generation with `java -jar schemaspy-6.0.0.jar`
8. Navigate to the created `output` folder and open the `index.html` in a browser of your choice
