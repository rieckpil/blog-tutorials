# Codebase for the blog post [#HOWTO: Deploy Java EE applications to Kubernetes](https://rieckpil.de/howto-deploy-java-ee-applications-to-kubernetes)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `java-ee-kubernetes-deployment`
3. Make sure you have Docker with Kubernetes support running on your machine
4. Create a local Docker registry: `docker run -d -p 5000:5000 --restart=always --name registry registry:2`
5. Build the application with `mvn clean package`
6. Build the Docker image with `docker build  -t java-ee-kubernetes .`
7. Tag and push the Docker image to your local registry `docker tag java-ee-kubernetes localhost:5000/java-ee-kubernetes`
8. Deploy the application with `kubectl apply -f deployment.yml`
9. Wait until the two pods are available and then visit http://localhost:31000/resources/sample
