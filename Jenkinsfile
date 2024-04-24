pipeline {
    agent any

    stages {
        stage('Build') { 
            steps {
                script {
                    
                    // Build frontend
                    dir('FrontEnd') {
                        bat 'npm install'
                    }
                }
            }
        }
        
        stage('Build Docker Images') { 
            parallel {
                stage('Build Docker FE image') {
                    steps {
                        dir ('FrontEnd') {
                            bat 'docker build -t leriad-react .'
                             // Run frontend container
                         dir ('FrontEnd') {
                            bat 'docker run -d -p 3000:3003 leriad-react'
                }
                        }
                    }
                }
                 stage('Test') { 
            steps {
                // Add a timeout to prevent hanging
                timeout(time: 10, unit: 'MINUTES') {
                    // Run backend tests
                    dir ('BackEnd') {
                        bat 'mvn test'
                    }
                   
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
            }
        }
        
        stage('Run Docker Containers') {
            steps {
               
                // Run backend container
                dir ('BackEnd') {
                    bat 'docker run -d -p 8000:8082 leriad-spring'
                }
            }
        }
        
       
    }
    
    post {
        always {
            // Cleanup: stop and remove Docker containers
            script {
                // Build backend
                    dir ('BackEnd') {
                        bat 'mvn package -Dmaven.test.skip'
                    }
                bat 'docker stop $(docker ps -aq)'
                bat 'docker rm $(docker ps -aq)'
            }
        }
    }
}
