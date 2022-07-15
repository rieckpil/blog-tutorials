# Codebase for the blog post [Top 3 Maven Plugins To Ensure Quality and Security](https://rieckpil.de/top-3-maven-plugins-to-ensure-quality-and-security-for-your-project/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `maven-plugins-to-ensure-quality`
3. Execute `./mvnw verify`.
4. (Optional) Uncomment the properties inside the `pom.xml`, run `./mvnw verify` again to see the build fail
5. Run `./mvnw spotbugs:check` and then `./mvnw spotbugs:gui` to see the detected bugs
