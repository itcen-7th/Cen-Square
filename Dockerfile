# Build stage
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Gradle build 캐시 최적화
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon

# Gradle build
COPY src ./src
RUN gradle build --no-daemon -x test


# Runtime stage
FROM arm64v8/openjdk:17-jdk
WORKDIR /app

# jar 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 환경설정
ENV TZ=Asia/Seoul
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
