FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-admin
COPY ./mall-service/mall-admin/target/mall-admin.jar /mall/mall-admin/mall-admin.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18075
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-admin-service", "-Dskywalking.agent.instance_name=mall-admin-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-admin.jar", "--spring.profiles.active=docker"]
