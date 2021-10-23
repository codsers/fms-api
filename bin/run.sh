
# dev
java -jar /app/fmsapi.jar \
--spring.profiles.active=dev \
--APP_PORT=8888 \
--SQLITE_PATH=c:/fmsapi.db \
--MYSQL_HOST=192.168.1.1 \
--MYSQL_PORT=3306 \
--MYSQL_NAME=fmsapi \
--MYSQL_USER=root \
--MYSQL_PWD=xxx \
--FILE_HOME=c:/fms \
> /app/fmsapi.log &

#-----------------------------------------------
# prod
nohup \
java -jar /app/fmsapi.jar \
--spring.profiles.active=prod \
--APP_PORT=8000 \
--SQLITE_PATH=/app/fmsapi.db \
--MYSQL_HOST=192.168.1.1 \
--MYSQL_PORT=3306 \
--MYSQL_NAME=fmsapi \
--MYSQL_USER=root \
--MYSQL_PWD=xxx \
--FILE_HOME=/fms \
> /app/fmsapi.log &

tail -f /app/fmsapi.log
