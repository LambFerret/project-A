# project-A
Spring Web App- for monitoring NodeJS server contained by Docker, Kubernetes. 

## Abstract
project-A is fullstack application that using two backend server(JAVA Spring, NodeJS), with ReactJS and MongoDB. Main purpose of this project is:
  1. Implement CI/CD pipeline.
  2. Monitoring Server.
  3. Server auto-recovering using Kubernetes orchestration.
  4. Authentication, Authorization with Spring Security.
  5. Basic web CRUD technic using both REST API and GRAPH QL.
  6. Using external API (In this project, Gmail API).

## Architecture
React (front-end) -> Spring (back-end 1) -> NodeJS (back-end 2)
  - React only communicates with Spring server.
  - NodeJS server is monitored by Spring in Kubernetes and Docker environment.
  - Spring act as middle manager and is responsible for all communication between React and NodeJS.

## Why am I using these stacks?
- Spring, Spring Security : Korean Government Framework.
- NodeJS : To show an understanding of async backend server.
- React : comparatibly simple and well-known CSR.
- MongoDB : Flexible NoSQL DB. 
- AWS EC2 and CodePipeline : Automating CI/CD pipeline.
- Kubernetes with Docker : Managing container orchestration, automate error recovery. 

## Main Features

### JAVA Spring Server
Using Spring Boot and Spring Security, implement user authentication and authorization, basic CRUD bulletin board. This will also served as Rest API, GraphQL in HTTP. 
If developer (Me!) received email that has specific keyword, server will create password by that keyword. (In this case, tester's name) 
Monitor the Node server. If the user logs in as Admin, two buttons will appear. 
- When the user presses Button-A, Spring will send POST request with the input field's data. This triggers CI/CD pipeline so that can send input field's data into NodeJS server.  
- Button-B trigger intentional error. user can monitor auto error recovery by Kubernetes.
These all process will appeared Spring monitoring.

### NodeJS Server
Deploy automation of own server via AWS CodePipeline. 
Raise intentional error to test recovery of Kubernetes.

### Database
Store user data, simple bulletin data. 
Password will hashed using BCrypt and has expiration date.
Gmail will cron to read developers email. If the keyword appears, the automatically generated password will stored. 

### Orchestration
Docker: All applications (Spring, Node.js, MongoDB) are containerized for consistent environments.
Kubernetes: Automatically manages Docker-packaged containers, and if a fault occurs in the Node.js server, it automatically recovers.

