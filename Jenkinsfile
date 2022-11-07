pipeline {
    agent any
   
    parameters { 
        string(defaultValue: "https://github.com/yessine/tpAchat.git", description: 'Whats the github URL?', name: 'URL')
    }
 tools {
       maven  '3.2.5' 
      // sonar  'sonar'
    }
   
    
    stages {
        stage('Checkout Git repository') {
           steps {
                git branch: 'main', url: "${params.URL}"
            }
        }
    }
}
