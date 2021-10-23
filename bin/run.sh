
# dev
java -jar /app/fmsapi.jar \
--spring.profiles.active=dev \
--APP_PORT=8888 \
--SQLITE_PATH=D:/tmp/fmsapi.db \
--fms.admin.username=root \
--fms.admin.password=123456 \
--FILE_HOME=D:/tmp/fms

#-----------------------------------------------
# prod
nohup \
java -jar /app/fmsapi.jar \
--spring.profiles.active=prod \
--APP_PORT=8888 \
--SQLITE_PATH=/tmp/fmsapi.db \
--fms.admin.username=root \
--fms.admin.password=123456 \
--FILE_HOME=/tmp/fms \
> /app/fmsapi.log &

tail -f /app/fmsapi.log
