// #!/usr/bin/env groovy
// node {
//  agent {
//     docker {
//         image 'maven:3-alpine'
//         args '-v $HOME/.m2:/root/.m2'
//     }
//  }
//
//  properties([disableConcurrentBuilds()])
//  try {
//    namespace = "default"
//    projectId = "c-kzd9h:p-vd76s"
//    deployment = "kafka-producer-processor"
//    dockerFile = "Dockerfile"
//    imageName = "kafka-producer-processor"
//    registry = "chjplove"
//    version = "latest"
//    stage('Checkout Branch') {
//        checkout scm
//        sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
//    }
//    stage ('verify') {
//     sh "mvn clean verify sonar:sonar \
//           -Dsonar.projectKey=kafka-producer-processor \
//           -Dsonar.host.url=http://34.142.231.60:9001 \
//           -Dsonar.login=sqp_7e9824141ee7e59b4cbf47d3ff50e3643dbf9c3f"
//    }
//    stage('Build Image') {
//        sh "docker build -t ${registry}/${imageName}:${version} -f ${dockerFile} ."
//    }
//    stage('Push Image') {
//        sh "docker tag ${registry}/${imageName}:${version} ${registry}/${imageName}:${version}"
//        sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
//        sh "docker push ${registry}/${imageName}:${version}"
//    }
//    switch(env.BRANCH_NAME) {
//        case 'develop':
//             stage("Deploy") {
//                 sh "echo ${env.BRANCH_NAME}"
// //                 sh """curl -k --location --request POST '${env.RANCHER_API_URL}/project/${projectId}/workloads/deployment:${namespace}:${deployment}?action=redeploy' \
// //                         --header 'Authorization: Bearer ${env.RANCHER_API_TOKEN}'"""
//             }
//             break;
//
//        case 'uat':
//             stage("Deploy") {
//                 sh "echo ${env.BRANCH_NAME}"
//             }
//             break;
//
//        case 'main':
//            stage("Deploy") {
//                 sh "echo ${env.BRANCH_NAME}"
// //                 sh """curl -k --location --request POST '${env.RANCHER_API_URL}/project/${clusterId}:${projectId}/workloads/deployment:${namespace}:${deployment}?action=redeploy' \
// //                         --header 'Authorization: Bearer ${env.RANCHER_API_TOKEN}'"""
//            }
//            break;
//    }
//  } catch (e) {
//    currentBuild.result = "FAILED"
//    throw e
//  }
// }

pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
    stages {
       stage('Checkout Branch') {
            steps {
                checkout scm
                sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
            }
       }
    }
}