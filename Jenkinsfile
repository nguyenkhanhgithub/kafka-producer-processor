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
        sh "egrep -q '^FROM .* AS builder\$' ${dockerFile}"
        sh "docker build -t ${imageName} --target builder -f ${dockerFile} ."
        sh "docker build -t ${imageName}:${version} -f ${dockerFile} ."
    }
//     stage('push') {
//       sh """
//         docker push ${imageName}:${imageVersion}
//         docker tag ${imageName}:${imageVersion} ${imageName}:${imageVersion}
//         docker push ${imageName}:${version}
//         echo ${imageName}:${imageVersion}
//       """
//     }
  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
