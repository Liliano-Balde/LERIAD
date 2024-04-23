pipeline {
    agent any

    stages {
        stage('Build') { 
            steps {
                dir ('BackEnd') {
                    bat 'mvn package -Dmaven.test.skip'
                }
                dir('FrontEnd') {
                    bat 'npm install'
                }
            }
        }
        
        stage('Build Docker image') { 
            steps {
                dir ('BackEnd') {
                    bat 'docker build -t leriad-spring .'
                }
            }
        }
        
        stage('Run Docker container') {
            steps {
                dir ('BackEnd') {
                    bat 'docker run -p 8082:8082 leriad-spring'
                }
            }
        }
    }
}
