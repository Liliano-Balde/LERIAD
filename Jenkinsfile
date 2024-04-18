pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                dir ('BackEnd'){
                bat 'mvn package -Dmaven.test.skip'
                }
            }
        }
        stage('Build Frontend') {
            steps {
                dir('FrontEnd') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }
    }
}


