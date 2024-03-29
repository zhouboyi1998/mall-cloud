FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-clickhouse
COPY ./mall-middleware/mall-clickhouse/target/mall-clickhouse.jar /mall/mall-clickhouse/mall-clickhouse.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18096
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-clickhouse-service", "-Dskywalking.agent.instance_name=mall-clickhouse-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-clickhouse.jar", "--spring.profiles.active=docker"]
