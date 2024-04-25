pipeline {
    agent any

    stages {
        stage('Build Front End') { 
            steps {
                script {
                    dir('FrontEnd') {
                        bat 'npm install'
                    }
                }
            }
        }
        
        stage('Build & tag Docker Images') { 
            parallel {
                stage('Build Docker FE image') {
                    steps {
                        dir('FrontEnd') {
                            bat 'docker build -t leriad-react .'
                            bat 'docker tag leriad-react lb187/leriad-react:latest'
                            bat 'docker run --name react-container -d -p 3000:3000 leriad-react'
                        }
                    }
                }
                
                stage('Test') { 
                    steps {
                        // Add your test commands here
                        // e.g., mvn test for backend tests
                    }
                }
                
                stage('Build Docker BE image') {
                    steps {
                        dir('BackEnd') {
                            bat 'docker build -t leriad-spring .'
                            bat 'docker tag leriad-spring lb187/leriad-spring:latest'
                        }
                    }
                }
            }
        }
        
        stage('Run Docker BE Container') {
            steps {
                dir('BackEnd') {
                    bat 'docker run --name spring-container -d -p 8082:8082 leriad-spring'
                }
            }
        }   
        
        stage('Login & Push Images') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'cend', usernameVariable: 'usr')]) {
                        docker.withRegistry('https://registry-1.docker.io', 'dockerhub') {
                            docker.image('lb187/leriad-react:latest').push()
                            docker.image('lb187/leriad-spring:latest').push()
                        }
                    }
                }
            }
        }    
    }
    
    post {
        always {
            // Cleanup: stop and remove Docker containers
            script {
                bat 'docker stop react-container'
                bat 'docker rm react-container'
                bat 'docker stop spring-container'
                bat 'docker rm spring-container'
                dir('BackEnd') {
                    bat 'mvn package -Dmaven.test.skip'
                }
            }
        }
    }
}
