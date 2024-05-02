FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-storage
COPY ./mall-business/mall-storage/target/mall-storage.jar /mall/mall-storage/mall-storage.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18083
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-storage-service", "-Dskywalking.agent.instance_name=mall-storage-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-storage.jar", "--spring.profiles.active=docker"]
