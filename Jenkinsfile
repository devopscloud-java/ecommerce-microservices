pipeline {
    agent any

    tools {
        maven 'mymaven'
    }
    environment {
        DOCKERHUB_REPO = "yellammam"
        IMAGE_TAG = "${BUILD_NUMBER}"
        DOCKER_CREDS = credentials('dockerhub-cred')
    }
    stages {

        stage('Build JARs') {
            steps {
                sh 'cd service-registry-3 && mvn clean package -DskipTests'
                sh 'cd api-gateway-1 && mvn clean package -DskipTests'
                sh 'cd order-service && mvn clean package -DskipTests'
                sh 'cd payment-service && mvn clean package -DskipTests'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                sh 'mvn sonar:sonar'
            }
        }
    }

        stage('Build Docker Images') {
            steps {
                sh """
                docker build -t $DOCKERHUB_REPO/service-registry-3:$IMAGE_TAG service-registry-3
                docker build -t $DOCKERHUB_REPO/api-gateway-1:$IMAGE_TAG api-gateway-1
                docker build -t $DOCKERHUB_REPO/order-service:$IMAGE_TAG order-service
                docker build -t $DOCKERHUB_REPO/payment-service:$IMAGE_TAG payment-service
               
                """
            }
        }
         
        stage('Docker Login & Push') {
            steps {
                sh '''
                echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin
                '''

                sh """
                docker push $DOCKERHUB_REPO/service-registry-3:$IMAGE_TAG
                docker push $DOCKERHUB_REPO/api-gateway-1:$IMAGE_TAG
                docker push $DOCKERHUB_REPO/order-service:$IMAGE_TAG
                docker push $DOCKERHUB_REPO/payment-service:$IMAGE_TAG
                
                """
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose down --remove-orphans || true'
                sh 'docker compose up -d'
            }
        }

    }
}
