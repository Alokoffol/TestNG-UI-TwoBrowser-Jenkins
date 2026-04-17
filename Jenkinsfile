pipeline {
    agent any

    tools {
        maven 'Maven-3.9.0'
        jdk 'JDK-17'
    }

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'yandex', 'both'],
            description: 'Выбери браузер для тестов'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo '✅ Код скачан'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    if (params.BROWSER == 'both') {
                        sh 'mvn clean test'
                    } else {
                        sh "mvn clean test -Dbrowser=${params.BROWSER}"
                    }
                }
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            echo '📊 Тесты завершены'
        }
        success {
            echo '✅ Все тесты прошли успешно!'
        }
        failure {
            echo '❌ Есть упавшие тесты'
        }
    }
}