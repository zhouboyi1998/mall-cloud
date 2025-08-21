FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-turbine
COPY ./mall-operation/mall-turbine/target/mall-turbine.jar /mall/mall-turbine/mall-turbine.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 8702
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-turbine-service", "-Dskywalking.agent.instance_name=mall-turbine-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-turbine.jar", "--spring.profiles.active=docker"]
