FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-goodscenter
COPY ./mall-center/mall-goodscenter/target/mall-goodscenter.jar /mall/mall-goodscenter/mall-goodscenter.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18063
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-goodscenter-service", "-Dskywalking.agent.instance_name=mall-goodscenter-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-goodscenter.jar", "--spring.profiles.active=docker"]
