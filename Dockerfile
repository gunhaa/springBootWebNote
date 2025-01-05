FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드된 Spring Boot JAR 파일을 복사
COPY build/libs/web-0.0.1-SNAPSHOT.jar helloDev.jar

# JAR 파일 실행
CMD ["java", "-jar", "helloDev.jar"]
