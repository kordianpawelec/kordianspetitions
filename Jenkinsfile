pipeline {
    agent any

    environment {
        DEPLOY_PATH = '/usr/local/tomcat/webapps' // Path to Tomcat's webapps folder
        APP_NAME = 'kordianspetitions' // Name of the WAR file without .war extension
        TOMCAT_LOG = '/usr/local/tomcat/logs/catalina.out' // Path to Tomcat logs
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
                    // Ensure the WAR file exists
                    sh """
                    if [ ! -f target/${APP_NAME}.war ]; then
                        echo "Error: ${APP_NAME}.war not found in target directory!"
                        exit 1
                    fi
                    """

                    // Deploy the WAR file to Tomcat
                    sh """
                    echo "Deploying to local Tomcat server..."
                    sudo rm -f ${DEPLOY_PATH}/${APP_NAME}.war
                    sudo cp target/${APP_NAME}.war ${DEPLOY_PATH}/
                    sudo pkill -f tomcat || true
                    sudo /usr/local/tomcat/bin/startup.sh
                    echo "Deployment completed!"
                    """

                    // Output recent Tomcat logs for verification
                    sh """
                    echo "Last 50 lines of Tomcat logs:"
                    tail -n 50 ${TOMCAT_LOG}
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
