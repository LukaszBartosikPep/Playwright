pipeline {
    // Define the agent (Jenkins node) where the pipeline will run
    agent any

    // Define environment variables to use throughout the pipeline
    environment {
        DOCKER_IMAGE = "my-playwright-java-project"  // Name of the Docker image to build and use
        REPO_URL = "https://github.com/LukaszBartosikPep/Playwright.git"  // GitHub repository URL to pull the project from
    }

    stages {
        // Stage 1: Checkout the code from the GitHub repository
        stage('Checkout') {
            steps {
                git url: "${REPO_URL}"  // Pull the project code from the specified repository
            }
        }

        // Stage 2: Build the Docker image for the project
        stage('Build Docker Image') {
            steps {
                script {
                    // Create the Dockerfile dynamically within the pipeline (optional)
                    writeFile file: 'Dockerfile', text: '''
                    FROM maven:3.8.1-openjdk-11 AS build
                    WORKDIR /app
                    COPY . .
                    RUN mvn -Dtest=org.example.SauceDemoTest compile
                    mvn -Dtest=org.example.SauceDemoTest test
                    '''



                    // Build the Docker image using the dynamically created Dockerfile
                    sh "docker build -t ${DOCKER_IMAGE} ."

                }
            }
        }

        // Stage 3: Run the Playwright tests inside the Docker container
        stage('Run Tests') {
            steps {
                script {
                    // Run the Docker container, executing the tests inside it
                    // The '--rm' flag automatically removes the container once it exits
                    sh "docker run --rm ${DOCKER_IMAGE}"
                }
            }
        }

        // Stage 4: Post-actions to be performed after the test run
        stage('Post Actions') {
            steps {
                script {
                    // Additional post-test actions like archiving test results could be placed here
                    echo "Tests completed."
                }
            }
        }
    }


}
