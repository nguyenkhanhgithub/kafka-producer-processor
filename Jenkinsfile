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
        sh "docker build -t ${imageName}:${version} -f ${dockerFile} ."
    }
    stage('Push Image') {
        sh "docker tag ${registry}/${imageName}:${version} ${registry}/${imageName}:${version}"
        sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
        sh "docker push ${registry}/${imageName}:${version}"
    }
    switch(env.BRANCH_NAME) {
        case 'main':
            stage('Pull Image') {
                def containerExists = sh "docker container ls -a -fname=${imageName} -q"
                sh "echo container ${containerExists}"
                if (containerExists != "null") {
                    sh "docker stop ${containerExists}"
                    sh "docker container rm ${containerExists}"
                }
//
//                 imageExists = sh "docker images -q ${registry}/${imageName}"
//                 if (imageExists != "null") {
//                     sh "docker image rm ${imageExists}"
//                 }
//                 sh "docker pull ${registry}/${imageName}:${version}"
            }
            stage("Deploy") {
                sh "docker run -p 7001:7001 --name ${imageName} ${registry}/${imageName}:${version} -d"
            }
            break;
    }
  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
