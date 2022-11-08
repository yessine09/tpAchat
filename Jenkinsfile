pipeline {

       agent any


        stages{
            stage('Checkout GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'slimback',
                    url : 'https://github.com/yessine09/tpAchat.git';
                             }
                             }
                             }
                                      stage("Build"){
                                        steps {
                                                 sh 'mvn clean package'
                                                  sh 'mvn install package'



                                               }

                             }