pipeline {
    agent any

    tools {
        maven 'mymaven'
    }
    environment {
        DOCKERHUB_REPO = "yellammam/ecommerce-app"
        IMAGE_TAG = "${1.0}"
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

        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }
         stage('Build Docker Images') {
            steps {
                sh """
                docker build -t $DOCKERHUB_REPO/eureka-service:$IMAGE_TAG eureka-service
                docker build -t $DOCKERHUB_REPO/api-gateway:$IMAGE_TAG api-gateway
                docker build -t $DOCKERHUB_REPO/order-service:$IMAGE_TAG order-service
                docker build -t $DOCKERHUB_REPO/product-service:$IMAGE_TAG product-service
                
                """
            }
        }

        stage('Docker Login & Push') {
            steps {
                sh '''
                echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin
                '''

                sh """
                docker push $DOCKERHUB_REPO/eureka-service:$IMAGE_TAG
                docker push $DOCKERHUB_REPO/api-gateway:$IMAGE_TAG
                docker push $DOCKERHUB_REPO/order-service:$IMAGE_TAG
                docker push $DOCKERHUB_REPO/product-service:$IMAGE_TAG
                
                """
            }
        }

        stage('Deploy Containers') {
        steps {
            sh 'docker compose down --remove-orphans || true'
            sh 'docker rm -f eureka-service api-gateway order-service payment-service || true'
            sh 'docker compose up -d'
        }
    }

    }
}
