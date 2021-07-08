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
    }
}
