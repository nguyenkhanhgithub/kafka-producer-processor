#!/usr/bin/env groovy
pipeline {
    stages {
        stage {
            steps {
                script {
                    checkout scm
                    sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
                }
            }
        }
    }
}