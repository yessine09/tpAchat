pipeline {
    agent any
    
     environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "you-192.168.1.148:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
    }
    
    stages {
        stage('Git') {
            steps {
                // Get code from a GitHub repository
                git url: 'https://github.com/yessine09/tpAchat.git', branch: 'zainebboss'
               
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
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn -f'spring/pom.xml' package -DskipTests=false"
                }
                echo ":$BUILD_NUMBER"
            }
        }
     stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
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
    }
}