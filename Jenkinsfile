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
        stage("Scan") {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube') {
                    bat "mvn sonar:sonar -Dsonar.token=squ_f6d93f45a0d6faac1556c81d011063afbcb45bfc"
                }
            }
        }
        // This will work if Jenkins and Sonar are in the cloud
        //stage("Quality Gate") {
            //steps {
                //timeout(time: 2, unit: 'MINUTES') {
                    //waitForQualityGate abortPipeline: true
                //}
            //}
        //}
    }
    post {
        always {
            archiveArtifacts allowEmptyArchive: true,
                artifacts: "**/demo-0.0.1-SNAPSHOT.war"
        }
        success {
            jacoco(
                execPattern: '**/jacoco.exec',
                sourcePattern: '**/src/main'
            )
            deploy adapters: [tomcat9(path: '', url: 'http://localhost:8080')], contextPath: null, onFailure: false, war: '"**/demo-0.0.1-SNAPSHOT.war'
        }
    }
}