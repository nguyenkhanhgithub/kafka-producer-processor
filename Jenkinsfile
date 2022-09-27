#!/usr/bin/env groovy

node {
  properties([disableConcurrentBuilds()])

  try {

    project = "kafka-producer-processor"
    dockerRepo = "docker.io"
    dockerFile = "Dockerfile"
    imageName = "chjplove/kafka-producer-processor"
    namespace= "zasi"

    stage('checkout code') {
      checkout scm
      sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
    }

    stage('build') {
      sh """
        egrep -q '^FROM .* AS builder\$' ${dockerFile} \
          && docker build -t ${imageName}-stage-builder --target builder -f ${dockerFile} .
        docker build -t ${imageName}:${env.BRANCH_NAME} -f ${dockerFile} .
      """
    }
    stage('push') {
      sh """
        docker push ${imageName}:${env.BRANCH_NAME}
        docker tag ${imageName}:${env.BRANCH_NAME} ${imageName}:${env.BRANCH_NAME}-build-${buildNumber}
        docker push ${imageName}:${env.BRANCH_NAME}-build-${buildNumber}
      """
    }
    switch(env.BRANCH_NAME) {
      case 'develop':
        stage('deploy-dev') {
          sh """
            export IMAGE_BUILD="${imageName}:${env.BRANCH_NAME}-build-${buildNumber}"
            k8sctl _deploy_rancher_dev ${k8sCluster} ${project} ${namespace}
          """
        }
        break
      case 'master':
        stage('deploy-prod') {
          sh """
            export IMAGE_BUILD="${imageName}:${env.BRANCH_NAME}-build-${buildNumber}"
            k8sctl _deploy_rancher_prod ${k8sCluster} ${project} ${namespace}
          """
        }
        break
    }

  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
