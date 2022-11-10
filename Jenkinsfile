pipeline {
environment
           {    registry = "slimtana/devops_project"
                 registryCredential = 'dockerhub_id'
                dockerImage = ''


            }
       agent any


        stages{
            stage('Checkout GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'slimback',
                    url : 'https://github.com/yessine09/tpAchat.git';
                             }
                             }

            stage("Build"){
                   steps {
                              sh 'mvn clean package'
                              sh 'mvn install package'




                                  }
}
stage("SONAR"){
                                                             steps {
                                                                 sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=esprit '

                                                                    }
                                                        }
           stage("Tests JUnit / Mockito"){
                         steps {
                            sh 'mvn test'
                            }
                    }

  stage('Building our image') {
                  steps {
                        script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"

                         }
                       }
                      }
                     stage('Deploy our image') {

                   steps {

                      script {

                          docker.withRegistry( '', registryCredential ) {

                              dockerImage.push()

                                }

                                  }

                                          }

                     }
                     stage("nexus deploy"){
                                             steps {
                                                sh 'mvn deploy'

                                                   }
                                      }


 stage("docker compose"){
                                                     steps {
                                                         sh 'mvn clean package'
                                                        // sh 'sudo chmod 666 /var/run/docker.sock'
                                                         sh 'docker-compose up -d --build'


                                                     }

                           }



                    }
        }
