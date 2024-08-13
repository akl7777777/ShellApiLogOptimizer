# ShellApiLogOptimizer
一款针对于ShellApi的日志优化插件,可以提升查询以及统计速度


docker-compose启动方式


1.启动环境:进入到docker-compose/environment目录,有一个Dockerfile是用来构建IK分词器的,不要动他,直接在这个路径下执行docker-compose up -d 
环境启动成功后看一眼docker ps进程死了没,死了就看下报错日志,如果报路径无权限建议chmod 777 -R data

2.启动项目
