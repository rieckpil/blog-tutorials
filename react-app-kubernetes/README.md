# Codebase for the blog post [#HOWTO: Deploy a React application to Kuberntes](https://dev.to/rieckpil/deploy-a-react-application-to-kubernetes-in-5-easy-steps-516j)

Steps to run this project:

1. Clone this Git repository
2. Navigate to the folder `react-app-kubernetes`
3. Make sure your Docker engine with Kubernetes support is enabled
4. Build the React app with `npm run-script build`
5. Dockerize the application with `docker build -t my-react-app .`
6. (Optional if you don't deploy to a local cluster) Create a local Docker registry with `docker run -d -p 5000:5000 --restart=always --name registry registry:2`
7. Tag the Docker image with `docker tag my-react-app localhost:5000/my-react-app`
8. Push the Docker image with `docker push localhost:5000/my-react-app`
9. Create the Kubernetes service and deployment with `kubectl apply -f deployment.yaml`
10. Visit http://localhost:31000 to see your React application