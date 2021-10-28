FROM java:8
ARG JAR_FILE=./fmsapi.jar
COPY ${JAR_FILE} /app.jar
ENV spring.profiles.active=prod
ENV APP_PORT=8000
ENV INIT_SCHEMA=always
ENV SQLITE_PATH=/fmsapi/fmsapi.db
ENV fms.admin.username=root
ENV fms.admin.password=123456
ENV FILE_HOME=/fmsapi/fms/
ENV TZ=Asia/Shanghai
WORKDIR /fmsapi
ENTRYPOINT ["java","-jar","/app.jar"]
