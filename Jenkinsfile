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
//     stage('Build Image') {
//         sh "docker build -t ${registry}/${imageName}:${version} -f ${dockerFile} ."
//     }
//     stage('Push Image') {
//         sh "docker tag ${registry}/${imageName}:${version} ${registry}/${imageName}:${version}"
//         sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
//         sh "docker push ${registry}/${imageName}:${version}"
//     }
    switch(env.BRANCH_NAME) {
        case 'main':
            stage('Pull Image') {
                script {
                    def oldContainerID = sh(script: "docker ps -a -fname=${imageName} -q", returnStdout: true)
                    if ("${oldContainerID}" != '') {
                        echo "Deleting image id: ${oldImageID}..."
                        sh "docker stop ${oldContainerID}"
                        sh "docker container rm ${oldContainerID}"
                    }
                    def oldImageID = sh(script: "docker images -qf reference=${registry}/${imageName}:${version}",returnStdout: true)
                    if ("${oldImageID}" != '') {
                        echo "Deleting image id: ${oldImageID}..."
                        sh "docker rmi -f ${oldImageID}"
                    }
                    sh "docker pull ${registry}/${imageName}:${version}"
                }
//                 sh "docker ps -q --filter ancestor=${registry}/${imageName} | xargs -r docker stop"
//                 sh "docker ps -a | grep ${imageName} | cut -d ' ' -f 1 | xargs -r docker rm"
//                 sh """docker rm \$(docker ps -a -q --filter ancestor=${registry}/${imageName})"""
//                 sh """docker image rm \$(docker images -q ${registry}/${imageName})"""
//                 sh "docker pull ${registry}/${imageName}:${version}"
            }
            stage("Deploy") {
                sh "docker run -p 7001:7001 --name ${imageName} -d ${registry}/${imageName}:${version}"
            }
            break;
    }
  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
