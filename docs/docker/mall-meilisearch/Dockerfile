FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-meilisearch
COPY ./mall-middleware/mall-meilisearch/target/mall-meilisearch.jar /mall/mall-meilisearch/mall-meilisearch.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18097
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-meilisearch-service", "-Dskywalking.agent.instance_name=mall-meilisearch-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-meilisearch.jar", "--spring.profiles.active=docker"]
