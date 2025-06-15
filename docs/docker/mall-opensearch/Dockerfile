FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-opensearch
COPY ./mall-middleware/mall-opensearch/target/mall-opensearch.jar /mall/mall-opensearch/mall-opensearch.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18098
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-opensearch-service", "-Dskywalking.agent.instance_name=mall-opensearch-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-opensearch.jar", "--spring.profiles.active=docker"]
