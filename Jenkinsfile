pipeline {
    environment {
        registry = "dariofrongillo/hello_ci"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    agent any
    triggers {
         pollSCM('* * * * *')
    }
    stages {
        stage("Initialization") {
            steps {
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

        stage('Build & Push Docker image') {
            when { tag "release-*" }
            steps {
              script {
                dockerImage = docker.build registry + ":$BUILD_NUMBER"
               }

             }

			}
    }
}


