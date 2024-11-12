pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clone the GitHub repository
                git 'https://github.com/kordianpawelec/kordianspetitions.git'
            }
        }

        stage('Build') {
            steps {
                // Clean, compile, and package the application
                sh 'mvn clean compile package'
            }
        }

        stage('Test') {
            steps {
                // Run tests
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Deploy WAR file to a specified server (adjust commands as necessary)
                // Assuming a deployment script or command here
                sh 'echo "Deploying to server..."'
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
