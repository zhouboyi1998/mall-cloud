FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-canal
COPY ./mall-middleware/mall-canal/target/mall-canal.jar /mall/mall-canal/mall-canal.jar
COPY ./skywalking-agent /mall/skywalking-agent

ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-canal-service", "-Dskywalking.agent.instance_name=mall-canal-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-canal.jar", "--spring.profiles.active=docker"]
