#!/usr/bin/env groovy

node {
  properties([disableConcurrentBuilds()])

  try {
    stage('checkout code') {
      checkout scm
      sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
    }
  } catch (e) {
    currentBuild.result = "FAILED"
    throw e
  }
}
