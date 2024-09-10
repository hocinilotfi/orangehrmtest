pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                // Cloner le dépôt GitHub contenant le code source
                git branch: 'main', credentialsId: '', url: 'https://github.com/hocinilotfi/orangehrmtest.git'
            }
        }
        stage('Build Maven Image') {
            steps {
                script {
                    try {
                        // Construire l'image Docker Maven pour les tests
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
                    try {
                        // Nettoyer les conteneurs Selenium existants
                        sh 'docker rm -f selenium-hub || true'

                        // Exécuter le conteneur Selenium Hub
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
                    try {
                        // Exécuter les tests Maven dans le conteneur Maven
                        sh 'docker run --rm --link selenium-hub:selenium-hub -v $WORKSPACE/target:/app/target maven-test-image mvn test -Dcucumber.plugin="json:target/cucumber-report/cucumber-report.json"'
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
            script {
                sh 'ls -R target/cucumber-report'
            }
            cucumber 'target/cucumber-report/cucumber-report.json'

            // Nettoyer les ressources Selenium Hub
            sh 'docker rm -f selenium-hub || true'
        }
    }
}
