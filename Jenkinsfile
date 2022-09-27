#!/usr/bin/env groovy

node {
  properties([disableConcurrentBuilds()])

  try {

    project = "kafka-producer-processor"
    dockerRepo = "docker.io"
    dockerFile = "Dockerfile"
    imageName = "chjplove/kafka-producer-processor"
    version = "latest"

    stage('Checkout Code') {
      checkout scm
      sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
    }
    stage('Build') {
        agent {
            docker {
                image 'mvn -Dmaven.test.skip=true clean package'
            }
      }
      steps {
      	sh 'mvn -Dmaven.test.skip=true clean package'
      }
    }
//     stage('build') {
//       sh """
//         docker build -t ${imageName} --target builder -f ${dockerFile} .
//         docker build -t ${imageName}:${version} -f ${dockerFile} .
//       """
//     }
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
