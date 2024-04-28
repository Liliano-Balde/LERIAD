pipeline {
    agent any

    stages {
        stage('Build Front End') { 
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
                            // Build frontend Docker image
                            bat 'docker build -t leriad-react .'
                            // Tag the Docker image
                            bat 'docker tag leriad-react lb187/leriad-react:latest'
                            // Run frontend container
                            dir ('FrontEnd') {
                            bat 'docker run --name react-container -d -p 3000:3000 leriad-react'
                             }
                        }
                    }
                }
               
        stage('Test') { 
                    steps {
                    // Run backend tests
                    dir ('BackEnd') {
                    bat 'mvn test'
                    
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
        
        stage('Deploy') {
            steps {
               
                // Run backend container
                dir ('BackEnd') {
                    bat 'docker build -t leriad-spring .'
                    bat 'docker tag leriad-spring lb187/leriad-spring:latest'
                    bat 'docker run --name spring-container -d -p 8082:8082 leriad-spring'

                }
            }
        }    
    }
    
    post {
        always {
            // Cleanup: stop and remove Docker containers
            script {
                // Build backend
                    bat 'docker stop react-container'
                    bat 'docker rm react-container'
                    bat 'docker stop spring-container'
                    bat 'docker rm spring-container'
                    dir ('BackEnd') {
                        bat 'mvn package -Dmaven.test.skip'
                        bat 'docker logout'
                    }
                }
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }

