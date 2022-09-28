#!/usr/bin/env groovy

node {
  properties([disableConcurrentBuilds()])
  try {
    project = "kafka-producer-processor"
    dockerFile = "Dockerfile"
    imageName = "chjplove/kafka-producer-processor"
    version = "latest"
    stage('Checkout') {
      checkout scm
      sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
    }
    stage('Build') {
            sh """
                egrep -q '^FROM .* AS builder\$' ${dockerFile} \
                && docker build -t ${imageName}:${version} -f ${dockerFile} .
            """
    }
  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
