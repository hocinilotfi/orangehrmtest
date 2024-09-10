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
                        // Monter le répertoire target du conteneur vers le workspace de Jenkins
                        sh 'docker run --rm --link selenium-hub:selenium-hub maven-test-image mvn test -Dcucumber.plugin="json:target/cucumber-report/cucumber-report.json"'
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
                // Afficher le contenu du dossier pour vérifier les fichiers de rapport
                sh 'ls -R $WORKSPACE/target || echo "Report directory not found"'
            }
            // Publier les résultats des tests Cucumber depuis le répertoire monté
            cucumber 'target/cucumber-report/cucumber-report.json'
        }
    }
}
