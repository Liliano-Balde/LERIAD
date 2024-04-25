pipeline {
    agent any

    stages {
        stage('Build Front End') { 
            steps {
                script {
                    // Build frontend
                    // bat 'docker stop react-container'
                    // bat 'docker rm react-container'
                    // bat 'docker stop spring-container'
                    // bat 'docker rm spring-container'
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
                        dir ('FrontEnd') {
                            bat 'docker build -t leriad-react .'
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
                        //just testing
                // Add a timeout to prevent hanging
                // timeout(time: 10, unit: 'MINUTES') {
                    // Run backend tests
                    dir ('BackEnd') {
                        bat 'mvn test'
                        // bat '.\mvnw test'
                }
            }
        }
        stage('Build Docker BE image') {
                    steps {
                        dir ('BackEnd') {
                            bat 'docker build -t leriad-spring .'
                            bat 'docker tag leriad-spring lb187/leriad-spring:latest'
                        }
                    }
                }
            }
        }
        
        stage('Run Docker BE Container') {
            steps {
               
                // Run backend container
                dir ('BackEnd') {
                    bat 'docker run --name spring-container -d -p 8082:8082 leriad-spring'
                }
            }
        }   
        stage('Login'){
            steps{
                withCredentials([string(credentialsId: env.DOCKERHUB_CREDENTIALS, variable: 'DOCKERHUB_CREDENTIALS')]) {
                        sh "echo ${DOCKERHUB_CREDENTIALS} | docker login -u lb187 --password-stdin"
                // bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
    }
    stage('Push Images') {
            steps {
                 bat 'docker push lb187/leriad-react:latest'
                bat 'docker push lb187/leriad-spring:latest'
                // // Run backend container
                // withCredentials([string(credentialsId: 'lb187', variable: 'dockerhubpwd')]) {
                // // bat 'docker login -u lb187 p ${dockerhubpwd}'
                // bat 'docker push lb187/leriad-react:latest'
                // bat 'docker push lb187/leriad-spring:latest'
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
    
