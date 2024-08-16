# ShellApiLogOptimizer

ShellApiLogOptimizer 是一款针对 ShellApi 的日志优化插件，可以显著提升查询及统计速度。

## 项目介绍

本插件通过优化日志存储和检索机制，大幅提高了 ShellApi 的日志处理性能。它特别适用于大规模日志数据的快速查询和分析。

## 配置要求

- 推荐配置：4核CPU，8GB内存以上；32GB内存以上效果最佳
- 建议使用 docker-compose 进行部署和管理

## 环境准备

在启动前，请确保已安装 Docker 和 Docker Compose。如果您尚未安装，请参考 [Docker 官方文档](https://docs.docker.com/engine/install/centos/)。

## 启动方式

### 使用 Docker Compose

1. 克隆项目代码
   ```shell
   git clone https://github.com/akl7777777/ShellApiLogOptimizer.git
   ```

2. 进入项目根目录
   ```shell
   cd ShellApiLogOptimizer
   ```

3. 启动环境（如果已部署外部 Elasticsearch，可跳过此步骤）
   修改 `docker-compose/environment/docker-compose.yml` 中的 ES 配置，将 ES 地址更改为您的实际地址。
   ```shell
   cd docker-compose/environment
   docker compose up -d
   ```

4. 启动项目
   返回项目根目录并运行：
   ```shell
   docker compose up -d
   ```
   项目将在 8080 端口运行。

### 使用 JAR 包启动

1. 下载最新的 JAR 包
   从 [Releases 页面](https://github.com/akl7777777/ShellApiLogOptimizer/releases) 下载最新的 JAR 文件。

2. 启动 JAR 包
   使用以下命令启动 JAR 包，根据你的需求调整参数：

   ```shell
   java -jar ShellApiLogOptimizer-x.x.x.jar \
   --server.port=8080 \
   --server.servlet.context-path=/shellApiLogOptimizer \
   --easy-es.enable=true \
   --easy-es.address=your_elasticsearch_address:9200 \
   --easy-es.username=your_username \
   --easy-es.password=your_password \
   --api.auth.token=your_api_token
   ```

   请将 `x.x.x` 替换为实际的版本号，并根据你的环境修改以下参数：
   - `server.port`: 应用运行的端口号
   - `server.servlet.context-path`: 应用的上下文路径
   - `easy-es.address`: Elasticsearch 服务器地址
   - `easy-es.username`: Elasticsearch 用户名
   - `easy-es.password`: Elasticsearch 密码
   - `api.auth.token`: API 认证令牌

3. 访问应用
   启动成功后，可以通过 `http://localhost:8080/shellApiLogOptimizer` 访问应用（假设你使用了默认的端口和上下文路径）。

注意：
- 使用 JAR 包启动时，请确保你的环境中已安装了 Java 11 或更高版本。
- 可以根据需要添加或删除命令行参数。
- 为了安全起见，建议在生产环境中使用更复杂的 API 认证令牌。

## 配置项说明

以下是主要配置项的说明，这些配置可以在 `docker-compose.yml` 文件中的 `environment` 部分进行设置，或在使用 JAR 包启动时作为命令行参数提供：

- `SERVER_SERVLET_CONTEXT_PATH` / `server.servlet.context-path`: 应用的上下文路径，默认为 "/shellApiLogOptimizer"
- `API_VERSION` / `api.version`: API 版本，默认为 "service"
- `API_AUTH_TOKEN` / `api.auth.token`: API 认证令牌，用于安全验证
- `EASY_ES_ENABLE` / `easy-es.enable`: 是否启用 Easy-ES，默认为 "true"
- `EASY_ES_ADDRESS` / `easy-es.address`: Elasticsearch 服务器地址，格式为 "host:port"
- `EASY_ES_USERNAME` / `easy-es.username`: Elasticsearch 用户名
- `EASY_ES_PASSWORD` / `easy-es.password`: Elasticsearch 密码

## 注意事项

- 确保 Elasticsearch 服务正常运行且可访问
- 如使用外部 Elasticsearch，请相应调整 `EASY_ES_ADDRESS`、`EASY_ES_USERNAME` 和 `EASY_ES_PASSWORD`
- 为保证安全，建议修改默认的 `API_AUTH_TOKEN`

## 贡献

欢迎提交 issues 和 pull requests 来帮助改进这个项目。

## 许可证

本项目采用 GNU Affero General Public License v3.0 (AGPL-3.0) 许可证。

我们欢迎并鼓励所有形式的开源贡献。选择 AGPL 许可证是为了确保任何基于本项目的改进都能回馈到开源社区。这不会影响您贡献代码的能力，但要求任何修改过的版本在通过网络提供服务时也必须开源。

完整的许可证文本可以在项目根目录的 [LICENSE](LICENSE) 文件中找到。