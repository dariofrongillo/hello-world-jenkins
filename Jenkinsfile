pipeline {
    agent any
    triggers {
         pollSCM('* * * * *')
    }
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
                sh "./mvnw clean compile sonar:sonar"
                }
            }
        }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
        stage("Unit Test + Integration Test") {
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

        stage('Deploy') {
          when {
            buildingTag()
          }
          steps {
            echo 'Ciao Deploy'
          }
        }
    }
}
