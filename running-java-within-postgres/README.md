# Codebase for the blog post [#HOWTO: Writing PostgreSQL functions with Java using PL/Java](https://rieckpil.de/howto-writing-postgresql-functions-with-java-using-pl-java/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `running-java-within-postgres`
3. Start your docker daemon
4. Build the sample docker image with `docker build -t pljava .` (*HINT*: This may take some minutes)
5. Start a docker container with `docker run -p 5432:5432 -e POSTGRES_PASSWORD=postgres pljava`
6. Wait until the database is ready to accept connections
7. Connect to the local database via [PgAdmin](https://www.pgadmin.org/download/) or `psql` CLI tool with user `postgres` and password `postgres`

```bash
psql -U postgres
```

8. Execute the functions within the `postgres` database and `public` schema:

```sql
SELECT greet('Your Name');

SELECT split_string_by_delimiter('hello - world - split - me', '-');
SELECT split_string_by_delimiter('hello < world < split < me', null);

SELECT get_oldest_person();
```


**PLEASE NOTE**: This is just a quick example to show you what's possible with `PL/Java`. For use in production make sure you read the [documentation](https://tada.github.io/pljava/) for potential security adjustments.