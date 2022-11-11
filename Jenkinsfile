pipeline {
    agent any
     tools{
    	maven "M2_HOME"
    }
    
     environment {
         registry = "zaineb12/alpine" 
        registryCredential = 'dockerhub_id' 
        dockerImage = '' 
       
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.1.148:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
    }
    
    stages {
        stage('Git') {
            steps {
                // Get code from a GitHub repository
                git url: 'https://github.com/yessine09/tpAchat.git', branch: 'zaineb'
               
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
                    sh "mvn -f'pom.xml' clean -DskipTests=true"
                }
            }
        }
        stage("Maven Compile") {
            steps {
                script {
                    sh "mvn -f'pom.xml' compile -DskipTests=true"
                }
            }
        }
       
        stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                }
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    sh 'mvn clean package'
                    sh 'mvn install package'
                }
                echo ":$BUILD_NUMBER"
            }
        }
    stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "spring/target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
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
        
        stage("Docker-compose") {
                steps{
                    sh 'docker-compose up -d'
                } 
           }
           
             stage('MOCKITO') {
            steps {
               sh 'echo skipMOCKITO'  // sh 'mvn clean test -Dtest=spring/com.esprit.spring.SecteurServiceMockitoTest' //
            }
        }
               
    }
    
}
