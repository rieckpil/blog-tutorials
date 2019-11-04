# Codebase for the blog post [#REVIEW: Top 3 Maven Plugins to ensure Quality and Security for your project](https://rieckpil.de/top-3-maven-plugins-to-ensure-quality-and-security-for-your-project/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `maven-plugins-to-ensure-quality`
3. Execute `mvn clean verify`. The build should fail as we still have JUnit 4 on the classpath. Fix it by uncommenting the exclusion of the JUnit vintage engine inside the `pom.xml`
4. The build should now pass
5. Run `spotbugs:check` and then `spotbugs:gui` to see the detected bugs