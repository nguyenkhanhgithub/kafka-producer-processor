#!/usr/bin/env groovy
pipeline {
    properties([disableConcurrentBuilds()])
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
    stage('Push Image') {
        sh "docker tag ${registry}/${imageName}:${version} ${registry}/${imageName}:${version}"
        sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
        sh "docker push ${registry}/${imageName}:${version}"
    }
    switch(env.BRANCH_NAME) {
        case 'main':
            stage('Pull Image') {
                script {
                    def oldContainerID = sh(script: "docker ps -a -fname=${imageName} -q", returnStdout: true)
                    if ("${oldContainerID}" != '') {
                        echo "Deleting image id: ${oldContainerID}..."
                        sh "docker stop ${oldContainerID}"
                        sh "docker container rm ${oldContainerID}"
                    }
                    def oldImageID = sh(script: "docker images -qf reference=${registry}/${imageName}:${version}",returnStdout: true)
                    if ("${oldImageID}" != '') {
                        echo "Deleting image id: ${oldImageID}..."
                        sh "docker rmi -f ${oldImageID}"
                        sh "docker rmi \$(docker images -qf reference=${registry}/${imageName} -q)"
                    }
                }
            }
            stage("Deploy") {
                sh """curl -k --location --request POST 'https://35.186.146.185/v3/project/c-zmk9v:p-fbbrp/workloads/deployment:default:kafka-producer-processor?action=redeploy' \
                        --header 'Authorization: Bearer token-q2w6j:xm6xqs2tdjsw9vrbm9mm9l98g6hgw2fw7j29crbhn45sd44gjrf9vx'"""
            }
            break;
    }
}
