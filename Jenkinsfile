pipeline{
    agent any
   
   stages {
        
        stage("Maven Clean") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' clean -DskipTests=true"
                }
            }
        }
        stage("Maven Compile") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' compile -DskipTests=true"
                }
            }
        }
        stage("Maven test") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' test"
                }
            }
        }
        stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                }
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' package -DskipTests=false"
                }
                echo ":$BUILD_NUMBER"
            }
        }

   }

}
