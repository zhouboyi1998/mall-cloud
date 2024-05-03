FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-foundation
COPY ./mall-business/mall-foundation/target/mall-foundation.jar /mall/mall-foundation/mall-foundation.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18084
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-foundation-service", "-Dskywalking.agent.instance_name=mall-foundation-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-foundation.jar", "--spring.profiles.active=docker"]
