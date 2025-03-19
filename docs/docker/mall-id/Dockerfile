FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-id
COPY ./mall-service/mall-id/target/mall-id.jar /mall/mall-id/mall-id.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18074
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-id-service", "-Dskywalking.agent.instance_name=mall-id-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-id.jar", "--spring.profiles.active=docker"]
