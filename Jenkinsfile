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
                sh "echo $GIT_COMMIT"
            }
        }
        stage("Build & SonarQube analysis") {
            agent any
            steps {
               withSonarQubeEnv('SonarQube') {
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

        stage('Deploy') {
            when { tag "release-*" }
            steps {
                echo 'Deploying only because this commit is tagged...'
                echo 'make deploy'
            }
        }
    }
}
