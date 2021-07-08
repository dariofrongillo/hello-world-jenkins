pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh "chmod +x mvnw"
                sh "./mvnw compile"
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
