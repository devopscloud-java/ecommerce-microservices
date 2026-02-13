pipeline {
    agent any

    tools {
        maven 'mymaven'
    }

    stages {

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
                sh 'docker compose down || true'
                sh 'docker compose up -d'
            }
        }
    }
}
