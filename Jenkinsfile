pipeline {
    agent any
    
    
   
           stage("Maven Test") {
            steps {
                script {
                    sh "mvn -version"
                }
            }
        }
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