FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-manager
COPY ./mall-business/mall-manager/target/mall-manager.jar /mall/mall-manager/mall-manager.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18085
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-manager-service", "-Dskywalking.agent.instance_name=mall-manager-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-manager.jar", "--spring.profiles.active=docker"]
