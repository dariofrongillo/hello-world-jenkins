pipeline {
    agent any
    stages {
        stage("Initialization") {
            steps {
                sh "chmod +x mvnw"
                sh "ls -la"
            }
        }
        stage("Build & SonarQube analysis") {
            agent any
            steps {
               withSonarQubeEnv('SonarQube') {
                sh "chmod +x mvnw"
                sh "./mvnw clean package sonar:sonar"
                }
            }
        }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
        stage("Unit Test") {
            steps {
                sh "chmod +x mvnw"
                sh "./mvnw test"
            }
        }
         stage("Code Coverage") {
            steps {
                sh "chmod +x mvnw"
                sh "./mvnw verify"
                publishHTML(target: [
                   reportDir: 'target/site/jacoco',
                   reportFiles: 'index.html',
                   reportName: "Jacoco Report"
                ])
             }
         }
    }
}
