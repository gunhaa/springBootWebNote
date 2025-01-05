pipeline {
    agent any

    environment {
        // 필요한 환경 변수 설정
        DOCKER_COMPOSE_FILE = 'docker-compose.yml' // docker-compose 파일 경로
        IMAGE_NAME = 'spring-boot-web' // Docker 이미지 이름
        CONTAINER_NAME = 'spring-boot-container' // Docker 컨테이너 이름
    }

    stages {
        stage('Checkout') {
            steps {
                // Git 레포지토리에서 소스 코드 체크아웃
                git 'https://github.com/your-repository/your-project.git'
            }
        }

        stage('Pull MySQL Image') {
            steps {
                script {
                    // MySQL 이미지 풀
                    sh 'docker pull mysql:latest'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드
                    echo 'Building Docker Image...'
                    sh 'docker-compose -f $DOCKER_COMPOSE_FILE build'
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    // Docker Compose로 컨테이너 실행
                    echo 'Starting Docker Compose...'
                    sh 'docker-compose -f $DOCKER_COMPOSE_FILE up -d'
                }
            }
        }

        stage('Post-Run Tests') {
            steps {
                script {
                    // 컨테이너가 정상적으로 실행되고 있는지 확인 (필요시 테스트)
                    echo 'Running Post-Run Tests...'
                    sh 'docker ps -a'  // 컨테이너 상태 확인
                }
            }
        }
    }

    post {
        always {
            // 빌드 후 필요한 작업
            echo 'Cleaning up...'
            sh 'docker-compose -f $DOCKER_COMPOSE_FILE down'  // 컨테이너 종료
        }
    }
}
