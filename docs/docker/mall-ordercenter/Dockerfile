FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-ordercenter
COPY ./mall-center/mall-ordercenter/target/mall-ordercenter.jar /mall/mall-ordercenter/mall-ordercenter.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18061
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-ordercenter-service", "-Dskywalking.agent.instance_name=mall-ordercenter-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-ordercenter.jar", "--spring.profiles.active=docker"]
