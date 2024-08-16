#!/bin/sh

JAVA_OPTS=""

# 函数用于添加非空的环境变量作为 Java 选项
add_java_opt() {
    if [ ! -z "$2" ]; then
        JAVA_OPTS="$JAVA_OPTS --$1=$2"
    fi
}

# 添加配置项
add_java_opt "server.servlet.context-path" "$SERVER_SERVLET_CONTEXT_PATH"
add_java_opt "api.version" "$API_VERSION"
add_java_opt "api.auth.token" "$API_AUTH_TOKEN"
add_java_opt "easy-es.enable" "$EASY_ES_ENABLE"
add_java_opt "easy-es.address" "$EASY_ES_ADDRESS"
add_java_opt "easy-es.username" "$EASY_ES_USERNAME"
add_java_opt "easy-es.password" "$EASY_ES_PASSWORD"

# 启动应用
exec java -jar app.jar $JAVA_OPTS
