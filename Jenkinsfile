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
   stage('Push Image') {
       sh "docker tag ${registry}/${imageName}:${version} ${registry}/${imageName}:${version}"
       sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
       sh "docker push ${registry}/${imageName}:${version}"
   }
   switch(env.BRANCH_NAME) {
       case 'main':
           stage('Pull Image') {
               script {
                   // check old container if exists and delete old container
//                    def oldContainerID = sh(script: "docker ps -a -fname=${imageName} -q", returnStdout: true)
//                    if ("${oldContainerID}" != '') {
//                        echo "Deleting image id: ${oldContainerID}..."
//                        sh "docker stop ${oldContainerID}"
//                        sh "docker container rm ${oldContainerID}"
//                    }

                    // check old image if exists and delete old image
//                    def oldImageID = sh(script: "docker images -qf reference=${registry}/${imageName}:${version}",returnStdout: true)
//                    if ("${oldImageID}" != '') {
//                        echo "Deleting image id: ${oldImageID}..."
//                        sh "docker rmi -f ${oldImageID}"
//                        sh "docker rmi \$(docker images -qf reference=${registry}/${imageName} -q)"
//                    }

                   // pull new image
//                    sh "docker pull ${registry}/${imageName}:${version}"
               }
           }
           stage("Deploy") {
               // run image
//                sh "docker run -p 7001:7001 --name ${imageName} -d ${registry}/${imageName}:${version}"
                rancherRedeploy alwaysPull: true, credentialId: '67e3a95c-316d-4e14-9366-f44b604fdf8e', images: 'chjplove/kafka-producer-processor:latest', workload: '/p/c-b57jq:p-nn6qt/workload/deployment:default:kafka-producer-processor'
           }
           break;
   }
 } catch (e) {
   currentBuild.result = "FAILED"
   throw e
 }
}
// https://35.186.146.185/v3 end point
//token-9qmxg:bnr24n7jpvr7wc7dwccb5mcs86ltm8mdx6rv9hmf6dckz6cvkvf96k