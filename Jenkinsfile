pipeline {
    agent any
    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    tools {
        maven 'Maven-3'
    }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Test') {
            steps { sh 'mvn -B test' }
            post {
                always { junit 'target/surefire-reports/*.xml' }
            }
        }
        stage('Build') {
            steps { sh 'mvn -B clean package -DskipTests' }
        }
        stage('Archive') {
            steps { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true }
        }
    }
    post {
        always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' }
        success { echo 'Pipeline succeeded: tests passed and taskapi.jar was built and archived.' }
        failure { echo 'Pipeline failed: check the Test or Build stage output above.' }
    }
}
