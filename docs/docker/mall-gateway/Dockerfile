FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-gateway
COPY ./mall-service/mall-gateway/target/mall-gateway.jar /mall/mall-gateway/mall-gateway.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18071
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-gateway-service", "-Dskywalking.agent.instance_name=mall-gateway-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-gateway.jar", "--spring.profiles.active=docker"]
