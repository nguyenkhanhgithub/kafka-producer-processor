#!/usr/bin/env groovy

node {
  properties([disableConcurrentBuilds()])

  try {

    project = "kafka-producer-processor"
    dockerRepo = "docker.io"
    dockerFile = "Dockerfile"
    imageName = "chjplove/kafka-producer-processor"
    version = "latest"

    stage('checkout code') {
      checkout scm
      sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
    }
    stage('build') {
//       sh """
//         docker build -t ${imageName} --target builder -f ${dockerFile} .
//         docker build -t ${imageName}:${version} -f ${dockerFile} .
//       """
    	agent {
      	docker {
        	image 'maven:3.6.1-jdk-8-alpine'
        }
        steps {
          sh 'mvn -Dmaven.test.skip=true clean package'
        }
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
