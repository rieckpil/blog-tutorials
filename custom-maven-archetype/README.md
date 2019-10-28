# Codebase for the blog post [#HOWTO: Create your own Maven Archetype in 5 simple steps](https://rieckpil.de/create-your-own-maven-archetype-in-5-simple-steps/)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `custom-maven-archetype`
3. Publish the Archetype to your local repository with `mvn clean install`
4. Use the Archetype to bootstrap a new project:
```
mvn archetype:generate \ 
  -DarchetypeGroupId=de.rieckpil.archetypes \ 
  -DarchetypeArtifactId=custom-maven-archetype \ 
  -DarchetypeVersion=1.0.0 \ 
  -DgroupId=de.rieckpil.blog \
  -DartifactId=new-project \ 
  -DinteractiveMode=false
```