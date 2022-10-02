#!/usr/bin/env groovy
pipeline {
   agent {
       docker {
           image 'maven:3.6.1-jdk-8-alpine'
           args '-v $HOME/.m2:/root/.m2'
       }
   }
   environment {
        PROJECT = "kafka-producer-processor"
        DOCKER_FILE = "Dockerfile"
        IMAGE_NAME = "kafka-producer-processor"
        REGISTRY = "chjplove"
        VERSION = "latest"
   }
   stages {
        stage('Checkout Branch') {
            steps {
                checkout scm
                sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
            }
        }
//         stage('Build Image') {
//             steps {
//                 sh "docker build -t ${REGISTRY}/${IMAGE_NAME}:${VERSION} -f ${DOCKER_FILE} ."
//             }
//         }
//         stage('Push Image') {
//             steps {
//                 sh "docker tag ${REGISTRY}/${IMAGE_NAME}:${VERSION} ${REGISTRY}/${IMAGE_NAME}:${VERSION}"
//                 sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
//                 sh "docker push ${REGISTRY}/${IMAGE_NAME}:${VERSION}"
//             }
//         }
//         switch(env.BRANCH_NAME) {
//             case 'main':
//                 stage('Pull Image') {
//                     steps {
//                         script {
//                             def oldContainerID = sh(script: "docker ps -a -fname=${imageName} -q", returnStdout: true)
//                             if ("${oldContainerID}" != '') {
//                                 echo "Deleting image id: ${oldContainerID}..."
//                                 sh "docker stop ${oldContainerID}"
//                                 sh "docker container rm ${oldContainerID}"
//                             }
//                             def oldImageID = sh(script: "docker images -qf reference=${registry}/${imageName}:${version}",returnStdout: true)
//                             if ("${oldImageID}" != '') {
//                                 echo "Deleting image id: ${oldImageID}..."
//                                 sh "docker rmi -f ${oldImageID}"
//                                 sh "docker rmi \$(docker images -qf reference=${registry}/${imageName} -q)"
//                             }
//                         }
//                     }
//                 }
//                 break;
//         }
//
        stage("Deploy") {
            steps {
                sh """curl -k --location --request POST 'https://35.186.146.185/v3/project/c-zmk9v:p-fbbrp/workloads/deployment:default:kafka-producer-processor?action=redeploy' \
                        --header 'Authorization: Bearer token-q2w6j:xm6xqs2tdjsw9vrbm9mm9l98g6hgw2fw7j29crbhn45sd44gjrf9vx'"""
            }
        }
   }
}
