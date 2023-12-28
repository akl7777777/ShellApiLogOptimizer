# 1. 使用Maven镜像构建项目
FROM maven:3.6.3-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# 2. 使用Tomcat镜像运行构建好的war文件
FROM tomcat:9.0-jdk11-openjdk-slim
COPY --from=build /home/app/target/ShellApiLogOptimizer-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
