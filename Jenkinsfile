pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git url: 'https://github.com/LukaszBartosikPep/Playwright.git'
            }
        }

        stage('Run Tests') {
            steps {
                // Run Maven tests directly
                sh 'mvn -Dtest=org.example.SauceDemoTest test'
            }
        }
    }

    post {
        always {
            // Archive test results to be displayed in Jenkins
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
