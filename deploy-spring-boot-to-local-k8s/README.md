# Deploy a Spring Boot application to a local Kubernetes Cluster

Install a local Kubernetes Cluster (with a single node) on your machine:

* For Linux: [MicroK8s](https://microk8s.io/docs/)
* For Mac/Windows: A local Kubernetes Cluster can be enabled with the latest versions of Docker for Windows/Mac

Steps to run this project:

1. Start your Docker daemon and ensure your local Kubernetes Cluster is up- and running
2. Create a local registry with `docker run -d -p 5000:5000 --restart=always --name registry registry:2`
3. Build the application with `mvn package`
4. Create the Docker image for this application `docker build -t spring-boot-app .`
5. Tag the Docker image (to be able to push it to the local registry) `docker tag spring-boot-app localhost:5000/spring-boot-app`
6. Push the Docker image to the local registry `docker push localhost:5000/spring-boot-app`
7. Execute `kubectl apply -f deployment.yml` to deploy the application to Kubernetes
8. Visit http://localhost:31000/api/messages or access it with `curl -v http://localhost:31000/api/messages`
