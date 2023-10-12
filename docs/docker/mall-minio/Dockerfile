FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-minio
COPY ./mall-middleware/mall-minio/mall-minio-core/target/mall-minio.jar /mall/mall-minio/mall-minio.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18091
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-minio-service", "-Dskywalking.agent.instance_name=mall-minio-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-minio.jar", "--spring.profiles.active=docker"]
