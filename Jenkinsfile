   
pipeline{
    agent any
   
   stages {
        
        stage("Maven Clean") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' clean -DskipTests=true"
                }
            }
        }
        stage("Maven Compile") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' compile -DskipTests=true"
                }
            }
        }
        stage("Maven test") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' test"
                }
            }
        }
        stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=Admin"
                }
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' package -DskipTests=false"
                }
                echo ":$BUILD_NUMBER"
            }
        }

   }

}
