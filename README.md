# LERIAD: Jenkins Pipeline Project

LERIAD is a Jenkins pipeline project aimed at implementing continuous integration and deployment for an application. This README provides an overview of the project, its contributors, and the current progress.

## Contributors
- Leo
- Rick
- Adele

## Project Overview
LERIAD focuses on setting up a robust CI/CD pipeline using Jenkins. The pipeline automates the build, test, and deployment processes, ensuring efficient development workflows. The goal is to streamline the development lifecycle and improve deployment reliability.

## Current Progress
- **Pipeline Setup**: A Jenkins pipeline has been created to manage the CI/CD workflow. This pipeline orchestrates the build, test, and deployment stages.
- **Containerisation**: The application containers have been Dockerised to facilitate deployment and ensure consistency across environments. Docker containers provide isolation and portability, making it easier to manage dependencies and deploy applications.
- **Integration Testing**: Implement automated integration tests to verify the application's functionality across different components.
- **Monitoring and Logging**: Integrate monitoring and logging solutions to track application performance and troubleshoot issues in real-time.

## Next Steps

- **Deployment Strategies**: Explore deployment strategies such as blue-green deployments or canary releases to minimize downtime and risk during deployment.
- **Scaling**: Plan for scalability by optimizing resource usage and implementing auto-scaling mechanisms to handle increased traffic loads.

## How to Use
1. **Clone the Repository**: Clone this repository to your local machine.
2. **Set up Jenkins**: Install and configure Jenkins on your server.
3. **Create Jenkins Pipeline**: Create a new pipeline project in Jenkins and point it to the Jenkinsfile in this repository.
4. **Configure Docker**: Ensure Docker is installed and running on your server or development environment.
5. **Build and Deploy**: Trigger the Jenkins pipeline to build and deploy the application containers.
