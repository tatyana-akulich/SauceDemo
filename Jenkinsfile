pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "3.8.6"
    }

parameters {
 gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
 booleanParam(defaultValue: true, name: 'HEADLESS')
 choice(choices: ['Sauce Labs Backpack', 'Sauce Labs Bike Light', 'Sauce Labs Bolt T-Shirt', 'Sauce Labs Fleece Jacket'],
 description: 'Choose a product to check, if it is displayed in catalog', name: 'ProductName')
 choice(choices: ['smoke.xml', 'regression.xml', 'testng.xml'], description: 'Choose xml file to run tests', name: 'XMLfile')
}

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: "${params.BRANCH}", url: 'https://github.com/tatyana-akulich/SauceDemo.git'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn clean test -DsuiteXmlFile=src/test/resources/${params.XMLfile}"
            }
        }
        stage('reports') {
            steps {
                script {
                    allure([
                     includeProperties: false,
                     jdk: '',
                     properties: [],
                     reportBuildPolicy: 'ALWAYS',
                     results: [[path: 'target/allure-results']]
                     ])
                }
            }
        }
    }
}
