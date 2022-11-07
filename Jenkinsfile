pipeline{
    agent any

    stages{

        stage('Cloning from GitHub') {
                steps {
                    git branch: 'yessine', url: 'https://github.com/yessine09/tpAchat.git'
                }
                
            }

      stage('Clean Maven'){
            steps {
                sh 'mvn clean '
            }
            
        }
        stage('Compile Project'){
            steps {
                sh 'mvn compile  -DskipTests'
            }
            
        }
        
        //stage("Build & Tests") {
        //    steps {
           //     sh 'mvn -Dmaven.test.failure.ignore=true clean install' 
            //}
            //post {
                //success {
                    //junit 'target/surefire-reports/**/*.xml' 
                //}
            //}
                    
        //}

        stage('Sonarqube Analysis') {
          steps {
            sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
          }
        }
}
}
