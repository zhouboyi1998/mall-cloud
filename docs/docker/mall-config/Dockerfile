FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-config
COPY ./mall-service/mall-config/target/mall-config.jar /mall/mall-config/mall-config.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 8888
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-config-service", "-Dskywalking.agent.instance_name=mall-config-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-config.jar", "--spring.profiles.active=docker"]
