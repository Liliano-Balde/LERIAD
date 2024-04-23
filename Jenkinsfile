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
        
        stage('Build Docker FE image') { 
            steps {
                dir ('FrontEnd') {
                    bat 'docker build -t leriad-react .'
                }
            }
        }
        stage('Run Docker FE container') { 
            steps {
                dir ('FrontEnd') {
                    bat 'docker run -p 3000:3000 leriad-react'
                }
            }
        }
        stage('Build Docker BE image') { 
            steps {
                dir ('BackEnd') {
                    bat 'docker build -t leriad-spring .'
                }
            }
        }
        stage('Run Docker BE container') {
            steps {
                dir ('BackEnd') {
                    bat 'docker run -p 8082:8082 leriad-spring'
                }
            }
        }
    }
}
