FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-binlog
COPY ./mall-middleware/mall-binlog/target/mall-binlog.jar /mall/mall-binlog/mall-binlog.jar
COPY ./skywalking-agent /mall/skywalking-agent

ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-binlog-service", "-Dskywalking.agent.instance_name=mall-binlog-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-binlog.jar", "--spring.profiles.active=docker"]
