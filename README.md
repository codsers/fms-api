# fms-api

- 用springboot构建的文件上传管理模块
- 旨在分离后台开发中文件上传下载问题


### 部署方式

``` bash
nohup \
java -jar /app/fmsapi.jar \
--spring.profiles.active=prod \
--APP_PORT=8888 \ # 服务端口
--SQLITE_PATH=/tmp/fmsapi.db \ # sqlite数据库文件路径
--fms.admin.username=root \ # 管理员web登录账号
--fms.admin.password=123456 \ # 管理员web登录密码
--FILE_HOME=/tmp/fms \ # 文件上传保存路径
> /app/fmsapi.log &

tail -f /app/fmsapi.log
```

