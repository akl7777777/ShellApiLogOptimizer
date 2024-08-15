# ShellApiLogOptimizer
一款针对于ShellApi的日志优化插件,可以提升查询以及统计速度

## 项目介绍

ShellApiLogOptimizer是一款针对于ShellApi的日志优化插件,可以提升查询以及统计速度

## 配置要求

- 建议4C8G内存以上机器,32G以上最佳
- 建议使用docker-compose启动

## 启动方式

0.下载源码
```shell
git clone https://github.com/akl7777777/ShellApiLogOptimizer.git
```

docker-compose启动

1.启动环境:进入到docker-compose/environment目录,有一个Dockerfile是用来构建IK分词器的,不要动他,直接在这个路径下执行docker compose up -d 
环境启动成功后看一眼docker ps进程死了没,死了就看下报错日志,如果报路径无权限建议chmod 777 -R data

2.启动项目
回到项目根目录 运行 docker compose up -d 项目运行在8080端口