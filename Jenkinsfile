pipeline {
    agent any

    environment {
        DEPLOY_PATH = '/usr/local/tomcat/webapps' // Path to Tomcat's webapps folder
        APP_NAME = 'kordianspetitions' // Name of the WAR file without .war extension
    }

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
                script {
                    // Copy the WAR file directly to Tomcat's webapps directory
                    sh """
                    echo "Deploying to local Tomcat server..."
                    sudo rm -f ${DEPLOY_PATH}/${APP_NAME}.war
                    sudo cp target/${APP_NAME}.war ${DEPLOY_PATH}/
                    sudo /usr/local/tomcat/bin/shutdown.sh || true
                    sudo /usr/local/tomcat/bin/startup.sh
                    echo "Deployment completed!"
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment completed successfully!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
