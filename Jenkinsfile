pipeline {
    agent any

    tools {
        maven 'Maven-3.9.5'
        jdk 'JDK-11'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser for test execution')
        choice(name: 'TAGS', choices: ['@Regression', '@Smoke', '@E2E', '@Login', '@Product'], description: 'Select test tags to execute')
        booleanParam(name: 'HEADLESS', defaultValue: false, description: 'Run tests in headless mode')
    }

    environment {
        BROWSER = "${params.BROWSER}"
        TAGS = "${params.TAGS}"
        HEADLESS = "${params.HEADLESS}"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "=========================================="
                    echo "Checking out code from repository"
                    echo "=========================================="
                }
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                script {
                    echo "=========================================="
                    echo "Cleaning previous build artifacts"
                    echo "=========================================="
                }
                bat 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                script {
                    echo "=========================================="
                    echo "Compiling the project"
                    echo "=========================================="
                }
                bat 'mvn compile'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo "=========================================="
                    echo "Executing Test Suite"
                    echo "Browser: ${BROWSER}"
                    echo "Tags: ${TAGS}"
                    echo "Headless: ${HEADLESS}"
                    echo "=========================================="
                }
                bat """
                    mvn test -Dbrowser=${BROWSER} -Dcucumber.filter.tags=${TAGS} -Dheadless=${HEADLESS}
                """
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    echo "=========================================="
                    echo "Generating Allure Report"
                    echo "=========================================="
                }
                bat 'mvn allure:report'
            }
        }
    }

    post {
        always {
            script {
                echo "=========================================="
                echo "Publishing Test Reports"
                echo "=========================================="
            }

            // Publish Allure Report
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])

            // Publish Extent Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/extent-reports',
                reportFiles: 'ExtentReport*.html',
                reportName: 'Extent Report',
                reportTitles: 'Test Execution Report'
            ])

            // Publish Cucumber Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/cucumber-reports',
                reportFiles: 'cucumber.html',
                reportName: 'Cucumber Report',
                reportTitles: 'Cucumber HTML Report'
            ])

            // Archive artifacts
            archiveArtifacts artifacts: '**/test-output/**/*.*, **/logs/**/*.log', allowEmptyArchive: true
        }

        success {
            script {
                echo "=========================================="
                echo "✓ BUILD SUCCESSFUL"
                echo "=========================================="
            }
            emailext(
                subject: "✓ SUCCESS: Jenkins Build ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: """
                    <h2>Build Successful</h2>
                    <p><b>Job Name:</b> ${env.JOB_NAME}</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Browser:</b> ${BROWSER}</p>
                    <p><b>Tags:</b> ${TAGS}</p>
                    <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <p><b>Allure Report:</b> <a href="${env.BUILD_URL}allure">View Report</a></p>
                """,
                to: 'qa-team@example.com',
                mimeType: 'text/html'
            )
        }

        failure {
            script {
                echo "=========================================="
                echo "✗ BUILD FAILED"
                echo "=========================================="
            }
            emailext(
                subject: "✗ FAILURE: Jenkins Build ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: """
                    <h2>Build Failed</h2>
                    <p><b>Job Name:</b> ${env.JOB_NAME}</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Browser:</b> ${BROWSER}</p>
                    <p><b>Tags:</b> ${TAGS}</p>
                    <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <p><b>Console Output:</b> <a href="${env.BUILD_URL}console">View Console</a></p>
                """,
                to: 'qa-team@example.com',
                mimeType: 'text/html'
            )
        }
    }
}