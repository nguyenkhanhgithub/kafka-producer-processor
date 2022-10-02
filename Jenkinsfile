#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage ('Initialize') {
            steps {
                checkout scm
                echo 'Placeholder.'
                script {
                    sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
                }
            }
        }
    }
}
