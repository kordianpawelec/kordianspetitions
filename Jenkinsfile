pipeline {
    agent any

    environment {
        DEPLOY_USER = 'ubuntu' // Adjust to your EC2 user
        DEPLOY_HOST = '16.171.153.161' // Replace with your EC2 public IP
        DEPLOY_KEY = '/path/to/keyAWS.pem' // Adjust to the location of your SSH private key on Jenkins
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
                    // Remove existing WAR file and copy the new one
                    sh """
                    echo "Deploying to Tomcat server..."
                    ssh -i ${DEPLOY_KEY} ${DEPLOY_USER}@${DEPLOY_HOST} "rm -f ${DEPLOY_PATH}/${APP_NAME}.war"
                    scp -i ${DEPLOY_KEY} target/${APP_NAME}.war ${DEPLOY_USER}@${DEPLOY_HOST}:${DEPLOY_PATH}/
                    ssh -i ${DEPLOY_KEY} ${DEPLOY_USER}@${DEPLOY_HOST} "sudo /usr/local/tomcat/bin/shutdown.sh; sudo /usr/local/tomcat/bin/startup.sh"
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
