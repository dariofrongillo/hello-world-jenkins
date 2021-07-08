pipeline {
    agent any
    stages {
        stage("build & SonarQube analysis") {
            agent any
            steps {
               withSonarQubeEnv('My SonarQube Server') {
                sh "chmod +x mvnw"
                sh "./mvnw clean package sonar:sonar"
                }
            }
        }
         stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
         }
        stage("Unit Test") {
            steps {
                sh "./mvnw test"
            }
        }
         stage("Code Coverage") {
            steps {
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
