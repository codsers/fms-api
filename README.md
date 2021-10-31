# fms-api

- 用springboot构建的文件上传管理模块
- 旨在分离后台开发中文件上传下载问题


### 部署方式

``` bash
nohup \
java -jar /app/fmsapi.jar \
--spring.profiles.active=prod \
--APP_PORT=8888 \ # 服务端口
--INIT_SCHEMA=never|always \ # 启动时是否自动创建表，never:不创建，always:创建
--SQLITE_PATH=/tmp/fmsapi.db \ # sqlite数据库文件路径
--fms.admin.username=root \ # 管理员web登录账号
--fms.admin.password=123456 \ # 管理员web登录密码
--FILE_HOME=/tmp/fms \ # 文件上传保存路径
> /app/fmsapi.log &

tail -f /app/fmsapi.log
```

### 使用方式
#### 登录web系统创建应用[http://127.0.0.1:8888/fms/admin]
![登录](https://z3.ax1x.com/2021/10/30/5xLlVg.png)
#### 应用管理
![](https://z3.ax1x.com/2021/10/30/5xOJTe.png)
#### 新建应用
![](https://z3.ax1x.com/2021/10/30/5xONYd.png)
#### 文件管理
![](https://z3.ax1x.com/2021/10/30/5xOBOf.png)
![](https://z3.ax1x.com/2021/10/30/5xOwlt.png)
![](https://z3.ax1x.com/2021/10/30/5xO8eO.png)

