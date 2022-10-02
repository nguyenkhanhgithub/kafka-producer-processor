#!/usr/bin/env groovy
pipeline {
    agent {
        docker {
            image 'maven:3.6.1-jdk-8-alpine'
            args '-v $HOME/.m2:/root/.m2'
            reuseNode true
        }
    }
    environment {
        DOCKER_REGISTRY = "chjplove"
        DOCKER_IMAGE_NAME = "kafka-producer-processor"
        DOCKER_IMAGE_VERSION = "latest"
        DOCKER_FILE = "Dockerfile"
    }
    stages {
        stage ('Initialize') {
            steps {
                checkout scm
                script {
                    sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
                }
            }
        }
        stage ('Build Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION} -f ${DOCKER_FILE} ."
                }
            }
        }
    }
}
