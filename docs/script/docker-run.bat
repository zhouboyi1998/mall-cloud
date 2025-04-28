@echo off
title Docker Run

@REM -------------------- mall-service --------------------

docker run -d -p 18071:18071 --name mall-gateway mall-gateway:latest
docker run -d -p 18072:18072 --name mall-security mall-security:latest
docker run -d -p 18073:18073 --name mall-user mall-user:latest
docker run -d -p 18074:18074 --name mall-id mall-id:latest
docker run -d -p 18077:18077 --name mall-captcha mall-captcha:latest

docker run -d -p 18075:18075 --name mall-admin mall-admin:latest
docker run -d -p 18076:18076 --name mall-swagger mall-swagger:latest

docker run -d -p 8761:8761 --name mall-eureka mall-eureka:latest
docker run -d -p 8888:8888 --name mall-config mall-config:latest

@REM -------------------- mall-middleware --------------------

docker run -d -p 18094:18094 --name mall-solr mall-solr:latest
docker run -d -p 18093:18093 --name mall-elasticsearch mall-elasticsearch:latest
docker run -d -p 18096:18096 --name mall-clickhouse mall-clickhouse:latest

docker run -d -p 18092:18092 --name mall-fastdfs mall-fastdfs:latest
docker run -d -p 18091:18091 --name mall-minio mall-minio:latest
docker run -d -p 18095:18095 --name mall-qiniu mall-qiniu:latest

docker run -d --name mall-binlog mall-binlog:latest
docker run -d --name mall-canal mall-canal:latest
docker run -d --name mall-debezium mall-debezium:latest

@REM -------------------- mall-business --------------------

docker run -d -p 18084:18084 --name mall-foundation mall-foundation:latest
docker run -d -p 18088:18088 --name mall-review mall-review:latest

docker run -d -p 18081:18081 --name mall-goods mall-goods:latest
docker run -d -p 18082:18082 --name mall-order mall-order:latest
docker run -d -p 18083:18083 --name mall-storage mall-storage:latest

docker run -d -p 18085:18085 --name mall-manager mall-manager:latest
docker run -d -p 18086:18086 --name mall-member mall-member:latest
docker run -d -p 18087:18087 --name mall-merchant mall-merchant:latest

@REM -------------------- mall-center --------------------

docker run -d -p 18061:18061 --name mall-ordercenter mall-ordercenter:latest
docker run -d -p 18062:18062 --name mall-openapicenter mall-openapicenter:latest
docker run -d -p 18063:18063 --name mall-goodscenter mall-goodscenter:latest

pause
