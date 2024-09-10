pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                // Cloner le dépôt GitHub contenant le code source et le Dockerfile
                git branch: 'main', credentialsId: '', url: 'https://github.com/hocinilotfi/orangehrmtest.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    try {
                        // Construire l'image Docker
                        sh 'docker build -t maven-test-image -f docker/Dockerfile .'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
        stage('Build Project') {
            steps {
                script {
                    try {
                        // Exécuter le conteneur Docker pour construire le projet avec mvn clean install
                        sh 'docker run --rm -v $PWD:/app -w /app maven-test-image mvn clean install'
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
                    try {
                        // Exécuter le conteneur Docker pour les tests Maven
                        sh 'docker run --rm -v $PWD:/app -w /app maven-test-image mvn test -Dcucumber.plugin="json:target/cucumber-report/cucumber-report.json"'
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
            // Publier les résultats des tests Cucumber
            cucumber buildStatus: 'FAILURE', fileIncludePattern: '**/target/cucumber-report/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
