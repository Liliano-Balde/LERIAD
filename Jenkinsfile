pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                bat 'mvn package -Dmaven.test.skip'
            }
        }
    }
}

