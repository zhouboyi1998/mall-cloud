FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-system
COPY ./mall-business/mall-system/mall-system-core/target/mall-system.jar /mall/mall-system/mall-system.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18084
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-system-service", "-Dskywalking.agent.instance_name=mall-system-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-system.jar", "--spring.profiles.active=docker"]
