pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                dir ('BackEnd'){
                bat 'mvn package -Dmaven.test.skip'
                }
                dir('FrontEnd') {
                bat 'npm install'
                }
                }
            }
        }
        
    }



