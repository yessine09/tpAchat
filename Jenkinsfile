pipeline {
           agent any

 
 

   
    stages {
        stage('Git') {
            steps {
                // Get code from a GitHub repository
                git url: 'https://github.com/yessine09/tpAchat.git', branch: 'jasmine'
               
            }
        }
              
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
                   
                   
                   
                   
                   
         stage("Upload War To Nexus"){
	    steps{
		script{
                                   pom = readMavenPom file: "spring/pom.xml";
		    nexusArtifactUploader artifacts: [
			[
			    artifactId: 'pom.artifactId', 
		            classifier: '', 
			    file: 'spring/target/*.${pom.packaging}', 
			    type: 'war'
			]
			], 
			    credentialsId: 'nexus3', 
			    groupId: 'pom.groupId', 
			    nexusUrl: '192.168.33.10:8081', 
			    nexusVersion: 'nexus3', 
			    protocol: 'http', 
			    repository: 'maven-releases'
            }
		}
	}
       
                   
                   

     
}
}
