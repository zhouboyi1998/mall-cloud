FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-security
COPY ./mall-service/mall-security/mall-security-core/target/mall-security.jar /mall/mall-security/mall-security.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18072
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-security-service", "-Dskywalking.agent.instance_name=mall-security-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-security.jar", "--spring.profiles.active=docker"]
