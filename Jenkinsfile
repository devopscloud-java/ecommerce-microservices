pipeline {
    agent any

    tools {
        maven 'mymaven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/devopscloud-java/ecommerce-microservices.git'
            }
        }

       stage('Build JARs') {
    steps {
        sh 'cd eureka-service && mvn clean package -DskipTests'
        sh 'cd api-gateway && mvn clean package -DskipTests'
        sh 'cd order-service && mvn clean package -DskipTests'
        sh 'cd payment-service && mvn clean package -DskipTests'
    }
}


        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy Containers') {
            steps {
                sh 'docker compose down'
                sh 'docker compose up -d'
            }
        }
    }
}
