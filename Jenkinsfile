pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', credentialsId: '', url: 'https://github.com/hocinilotfi/orangehrmtest.git'
            }
        }
        stage('Build Maven Image') {
            steps {
                script {
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
                    try {
                        sh 'docker run -d --name selenium-hub -p 4444:4444 selenium/standalone-chrome || echo "Selenium Hub already running"'
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
                        // Exécuter les tests Maven dans le conteneur
                        sh 'docker run --rm --link selenium-hub:selenium-hub maven-test-image mvn test -Dcucumber.plugin="json:target/cucumber-report/cucumber-report.json"'
                        // Copier les rapports depuis le conteneur vers Jenkins
                        sh 'docker cp $(docker ps -alq):/app/target $WORKSPACE/target'
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
            script {
                // Vérifier le contenu du répertoire des rapports
                sh 'ls -R $WORKSPACE/target || echo "Report directory not found"'
            }
            // Publier les rapports de tests Cucumber depuis le répertoire local
            cucumber 'target/cucumber-report/cucumber-report.json'
        }
    }
}
