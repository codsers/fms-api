docker run -dit \
--name fmsapi-test \
-p 9997:8000 \
-e fms.admin.username=user \
-e fms.admin.password=123456 \
-v /opt/codser/dms/fms:/fmsapi \
fmsapi:1.0.0