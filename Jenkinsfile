pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
                
        stage('Regression API Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/aakashb9999/APIAutomation1'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testRunner/testng.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
       
        
        
         stage("Deploy to STAGE"){
            steps{
                echo("deploy to STAGE done")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/aakashb9999/APIAutomation1'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testRunner/testng_sanity.xml"
                    
                }
            }
        }
        
          stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'reports', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Extent Report', 
                                  reportTitles: ''])
            }
        }

       
                
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}