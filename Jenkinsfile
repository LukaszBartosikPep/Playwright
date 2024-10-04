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
                    // Create Dockerfile (optional, you could use an existing Dockerfile instead)
                    writeFile file: 'Dockerfile', text: '''
                    FROM maven:3.8.1-openjdk-11 AS build
                    WORKDIR /app
                    COPY . .  // Copy project files to Docker container
                    RUN mvn compile  // Only compile the project, do not run tests here
                    '''
                    
                    // Build the Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run the tests inside the Docker container
                    sh "docker run --rm ${DOCKER_IMAGE} mvn -Dtest=org.example.SauceDemoTest test"
                }
            }
        }

        stage('Post Actions') {
            steps {
                script {
                    // Assuming test results are in target/surefire-reports/
                    junit '**/target/surefire-reports/*.xml'  // Archive JUnit test results

                    // Additional post-test actions, if any
                    echo "Tests completed."
                }
            }
        }
    }
}
