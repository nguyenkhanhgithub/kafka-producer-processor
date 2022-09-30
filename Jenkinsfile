#!/usr/bin/env groovy
node {
    agent {
        docker {
            image 'maven:3.6.1-jdk-8-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
  properties([disableConcurrentBuilds()])
  try {
    project = "kafka-producer-processor"
    dockerFile = "Dockerfile"
    imageName = "kafka-producer-processor"
    registry = "chjplove"
    version = "latest"
    stage('Checkout Branch') {
        checkout scm
        sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
    }
    stage('Build Image') {
        sh "docker build -t ${registry}/${imageName}:${version} -f ${dockerFile} ."
    }
  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
