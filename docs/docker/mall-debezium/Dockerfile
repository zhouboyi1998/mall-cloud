FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-debezium
COPY ./mall-middleware/mall-debezium/target/mall-debezium.jar /mall/mall-debezium/mall-debezium.jar
COPY ./skywalking-agent /mall/skywalking-agent

ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-debezium-service", "-Dskywalking.agent.instance_name=mall-debezium-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-debezium.jar", "--spring.profiles.active=docker"]
