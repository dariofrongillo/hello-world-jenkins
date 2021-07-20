pipeline {
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

        stage("Build & Push Docker image") {
                steps {
					withCredentials([usernamePassword(credentialsId: 'dockerhub_id', passwordVariable: 'pass', usernameVariable: 'user')]) {
						   sh 'docker login --username=$user --password=$pass'
						   sh "./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=dariofrongillo/hello_ci:$GIT_COMMIT"
						   sh 'docker push dariofrongillo/hello_ci:$GIT_COMMIT'


					}
                }



			}
    }
}


