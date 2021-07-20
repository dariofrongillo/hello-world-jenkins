pipeline {
     environment {
       REGISTRY_HOST = 'dariofrongillo'
       ARTIFACT_IMAGE_NAME = 'hello_ci'
     }
    agent any
    triggers {
         pollSCM('* * * * *')
    }
    stages {
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

        stage("Build Docker image") {
                steps {
					withCredentials([usernamePassword(credentialsId: 'dockerhub_id', passwordVariable: 'pass', usernameVariable: 'user')]) {
						   sh 'docker login --username=$user --password=$pass'
						   sh './mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=$REGISTRY_HOST/$ARTIFACT_IMAGE_NAME:$GIT_COMMIT'


					}
                }

			}

        stage("Release Docker image") {
                when { tag "release-*" }
                steps {
					withCredentials([usernamePassword(credentialsId: 'dockerhub_id', passwordVariable: 'pass', usernameVariable: 'user')]) {
						   sh 'docker login --username=$user --password=$pass'
						   sh 'docker push $REGISTRY_HOST/$ARTIFACT_IMAGE_NAME:$GIT_COMMIT'

					}
                }

			}
    }
}


