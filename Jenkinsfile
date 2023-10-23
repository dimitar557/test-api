pipeline {
    agent any
    tools {
        maven "Maven-3.8.4"
    }
    stages {
        stage("Source") {
            steps {
                git branch: "main",
                    changelog: false,
                    poll: true,
                    url: "https://github.com/dimitar557/test-api"
            }
        }
        stage("Clean") {
            steps {
                bat "mvn clean"
            }
        }
        stage("Test") {
            steps {
                bat "mvn test"
            }
        }
        stage("Package") {
            steps {
                bat "mvn package -DskipTests"
            }
        }
    }
    post {
        always {
            archiveArtifacts allowEmptyArchive: true,
                artifacts: "**/demo-1.0-SNAPSHOT.war"
        }
        success {
            jacoco(
                execPattern: '**/build/jacoco/*.exec',
                classPattern: '**/build/classes/java/main',
                sourcePattern: '**/src/main'
            )
            deploy adapters: [tomcat9(path: '', url: 'http://localhost:8080')], contextPath: null, onFailure: false, war: '"**/demo-0.0.1-SNAPSHOT.war'
        }
    }
}