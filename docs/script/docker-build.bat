@echo off
title Docker Build

@REM -------------------- mall-service --------------------

docker build -f ./docs/docker/mall-gateway/Dockerfile -t mall-gateway:latest .
docker build -f ./docs/docker/mall-security/Dockerfile -t mall-security:latest .
docker build -f ./docs/docker/mall-user/Dockerfile -t mall-user:latest .
docker build -f ./docs/docker/mall-id/Dockerfile -t mall-id:latest .
docker build -f ./docs/docker/mall-captcha/Dockerfile -t mall-captcha:latest .

docker build -f ./docs/docker/mall-admin/Dockerfile -t mall-admin:latest .
docker build -f ./docs/docker/mall-hystrix/Dockerfile -t mall-hystrix:latest .
docker build -f ./docs/docker/mall-turbine/Dockerfile -t mall-turbine:latest .
docker build -f ./docs/docker/mall-swagger/Dockerfile -t mall-swagger:latest .

docker build -f ./docs/docker/mall-eureka/Dockerfile -t mall-eureka:latest .
docker build -f ./docs/docker/mall-config/Dockerfile -t mall-config:latest .
docker build -f ./docs/docker/mall-zuul/Dockerfile -t mall-zuul:latest .

docker build -f ./docs/docker/mall-grinder/Dockerfile -t mall-grinder:latest .

@REM -------------------- mall-middleware --------------------

docker build -f ./docs/docker/mall-solr/Dockerfile -t mall-solr:latest .
docker build -f ./docs/docker/mall-elasticsearch/Dockerfile -t mall-elasticsearch:latest .
docker build -f ./docs/docker/mall-meilisearch/Dockerfile -t mall-meilisearch:latest .
docker build -f ./docs/docker/mall-opensearch/Dockerfile -t mall-opensearch:latest .
docker build -f ./docs/docker/mall-clickhouse/Dockerfile -t mall-clickhouse:latest .

docker build -f ./docs/docker/mall-fastdfs/Dockerfile -t mall-fastdfs:latest .
docker build -f ./docs/docker/mall-minio/Dockerfile -t mall-minio:latest .
docker build -f ./docs/docker/mall-qiniu/Dockerfile -t mall-qiniu:latest .

docker build -f ./docs/docker/mall-binlog/Dockerfile -t mall-binlog:latest .
docker build -f ./docs/docker/mall-canal/Dockerfile -t mall-canal:latest .
docker build -f ./docs/docker/mall-debezium/Dockerfile -t mall-debezium:latest .

@REM -------------------- mall-business --------------------

docker build -f ./docs/docker/mall-foundation/Dockerfile -t mall-foundation:latest .
docker build -f ./docs/docker/mall-review/Dockerfile -t mall-review:latest .

docker build -f ./docs/docker/mall-goods/Dockerfile -t mall-goods:latest .
docker build -f ./docs/docker/mall-order/Dockerfile -t mall-order:latest .
docker build -f ./docs/docker/mall-storage/Dockerfile -t mall-storage:latest .

docker build -f ./docs/docker/mall-manager/Dockerfile -t mall-manager:latest .
docker build -f ./docs/docker/mall-member/Dockerfile -t mall-member:latest .
docker build -f ./docs/docker/mall-merchant/Dockerfile -t mall-merchant:latest .

@REM -------------------- mall-center --------------------

docker build -f ./docs/docker/mall-ordercenter/Dockerfile -t mall-ordercenter:latest .
docker build -f ./docs/docker/mall-openapicenter/Dockerfile -t mall-openapicenter:latest .
docker build -f ./docs/docker/mall-goodscenter/Dockerfile -t mall-goodscenter:latest .

pause
