# Codebase for the blog post [#CHEATSHEET: Java/Jakarta EE application servers](https://rieckpil.de/cheatsheet-java-jakarta-ee-application-servers/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `application-server-cheatsheet`
3. Bulild the project with `mvn clean package`
4. Deploy it to a running application server of your choice and follow the steps described in the blog for creating the data source. Have a look at the specific JNDI names for the data source within the `persistence.xml` and `SampleResource.java`