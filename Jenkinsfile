pipeline {
    agent any

    stages {
        stage('Build Front End') { 
            steps {
                script {
                    // Build frontend
                    bat 'docker stop react-container'
                    bat 'docker rm react-container'
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

                            bat 'docker run --name react-container -d -p 3000:3000 leriad-react'
                             }
                        }
                    }
                }
               
        stage('Test') { 
                    steps {
                // Add a timeout to prevent hanging
                // timeout(time: 10, unit: 'MINUTES') {
                    // Run backend tests
                    dir ('BackEnd') {
                        // bat 'mvn test'
                        bat '.\mvnw test'
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
        
        stage('Run Docker BE Container') {
            steps {
               
                // Run backend container
                dir ('BackEnd') {
                    bat 'docker run -d -p 8082:8082 leriad-spring'
                }
            }
        }    
    }
    
    post {
        always {
            // Cleanup: stop and remove Docker containers
            script {
                // Build backend
                    // bat 'docker stop react-container'
                    // bat 'docker rm react-container'
                    dir ('BackEnd') {
                        bat 'mvn package -Dmaven.test.skip'
                    }
                }
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                junit '**/target/surfire-reports/TEST-*.xml'
            }
        }
    }

