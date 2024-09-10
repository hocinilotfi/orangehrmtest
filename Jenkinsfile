pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    echo 'Cloning the Git repository...'
                    git branch: 'main', credentialsId: '', url: 'https://github.com/hocinilotfi/orangehrmtest.git'
                }
            }
        }
        stage('Build Maven Image') {
            steps {
                script {
                    echo 'Building Docker Maven image...'
                    try {
                        sh 'docker build -t maven-test-image -f docker/Dockerfile .'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Run Selenium Server') {
            steps {
                script {
                    echo 'Starting Selenium server...'
                    try {
                        sh 'docker run -d --name selenium-hub -p 4444:4444 selenium/standalone-chrome'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    echo 'Running tests...'
                    try {
                        sh 'docker run --rm --link selenium-hub:maven-test-image maven-test-image mvn clean install test -Dcucumber.plugin="json:target/cucumber-report/cucumber-report.json"'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Publishing Cucumber reports...'
            cucumber 'target/cucumber-report/cucumber-report.json'
            
            echo 'Stopping and removing Selenium server container...'
            sh 'docker stop selenium-hub || true'
            sh 'docker rm selenium-hub || true'
        }
    }
}
