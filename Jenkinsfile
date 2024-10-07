pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "my-playwright-java-project"
        REPO_URL = "https://github.com/LukaszBartosikPep/Playwright.git"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: "${REPO_URL}"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Create Dockerfile (optional, if not present in the repository)
                    writeFile file: 'Dockerfile', text: '''
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn compile
'''
                    // Build the Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run the tests inside the Docker container and mount the workspace
                    sh "docker run --rm -v \$(pwd):/app ${DOCKER_IMAGE} mvn -Dtest=org.example.SauceDemoTest test"
                }
            }
        }
    }

    post {
        always {
            // Archive test results to be displayed in Jenkins
            junit '**/target/surefire-reports/*.xml'
            echo "Tests completed."
        }
    }
}
