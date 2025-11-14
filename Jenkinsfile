pipeline {
    agent any

    tools {
        // Names must match entries under "Manage Jenkins → Global Tool Configuration"
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
        // Optional: override if you configured MAVEN_HOME/JAVA_HOME manually on agents
        // MAVEN_HOME = tool name resolution already sets PATH to maven/bin
    }

    stages {
        stage('Checkout') {
            steps {
                echo "=== Checkout ==="
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                echo "=== Clean ==="
                script {
                    if (isUnix()) {
                        sh 'mvn -B -q clean'
                    } else {
                        bat 'mvn -B -q clean'
                    }
                }
            }
        }

        stage('Compile') {
            steps {
                echo "=== Compile ==="
                script {
                    if (isUnix()) {
                        sh 'mvn -B -q compile'
                    } else {
                        bat 'mvn -B -q compile'
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                echo "=== Running Tests ==="
                echo "Browser: ${BROWSER}, Tags: ${TAGS}, Headless: ${HEADLESS}"
                script {
                    def cmd = "mvn -B test -Dbrowser=${BROWSER} -Dcucumber.filter.tags=\"${TAGS}\" -Dheadless=${HEADLESS}"
                    if (isUnix()) {
                        sh cmd
                    } else {
                        bat cmd
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo "=== Generate Allure ==="
                script {
                    // If you use allure-maven plugin, mvn allure:report will work
                    if (isUnix()) {
                        sh 'mvn -B allure:report || true'
                    } else {
                        bat 'mvn -B allure:report || exit /b 0'
                    }
                }
            }
        }
    }

    post {
        always {
            echo "=== Publish Reports & Archive ==="
            // Publish Allure if plugin installed and results exist
            script {
                // Publish via Allure Jenkins plugin (set path results to target/allure-results)
                try {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                } catch (e) {
                    echo "Allure publish failed or plugin not installed: ${e}"
                }
            }

            // Publish Extent & Cucumber HTML using Publish HTML plugin
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/extent-reports',
                reportFiles: 'ExtentReport*.html',
                reportName: 'Extent Report'
            ])

            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/cucumber-reports',
                reportFiles: 'cucumber.html',
                reportName: 'Cucumber Report'
            ])

            archiveArtifacts artifacts: '**/target/**/*.*, **/test-output/**/*.*, **/logs/**/*.log', allowEmptyArchive: true
        }

        success {
            echo "=== BUILD SUCCESS ==="
            // Email plugin call; requires configured SMTP and plugin (emailext)
            script {
                try {
                    emailext (
                        subject: "✓ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """<p>Build successful.</p>
                                 <p>Browser: ${BROWSER}</p>
                                 <p>Tags: ${TAGS}</p>
                                 <p>Build URL: <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>""",
                        to: 'krishnaharshap11@gmail.com',
                        mimeType: 'text/html'
                    )
                } catch (e) {
                    echo "Email send failed: ${e}"
                }
            }
        }

        failure {
            echo "=== BUILD FAILED ==="
            script {
                try {
                    emailext (
                        subject: "✗ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """<p>Build failed. Console: <a href='${env.BUILD_URL}console'>console</a></p>""",
                        to: 'krishnaharshap11@gmail.com',
                        mimeType: 'text/html'
                    )
                } catch (e) {
                    echo "Email send failed: ${e}"
                }
            }
        }
    }
}
