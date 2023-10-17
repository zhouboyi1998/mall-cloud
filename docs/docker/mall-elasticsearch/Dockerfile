FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-elasticsearch
COPY ./mall-middleware/mall-elasticsearch/target/mall-elasticsearch.jar /mall/mall-elasticsearch/mall-elasticsearch.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18093
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-elasticsearch-service", "-Dskywalking.agent.instance_name=mall-elasticsearch-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-elasticsearch.jar", "--spring.profiles.active=docker"]
