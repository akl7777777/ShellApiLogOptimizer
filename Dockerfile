# 使用 Maven 镜像构建项目
FROM maven:3.6.3-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# 使用基础 Java 镜像运行 JAR 文件
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/ShellApiLogOptimizer-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080

# 设置环境变量（根据需要修改）
ENV SERVER_SERVLET_CONTEXT_PATH=/shellApiLogOptimizer \
    API_VERSION=service \
    EASY_ES_ENABLE=true \
    EASY_ES_ADDRESS=127.0.0.1:9200 \
    EASY_ES_USERNAME=elastic \
    EASY_ES_PASSWORD=YourElasticPassword

# 启动 Java 应用
CMD ["java", "-jar", "app.jar"]
