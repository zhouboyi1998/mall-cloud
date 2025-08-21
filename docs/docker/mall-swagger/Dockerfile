FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-swagger
COPY ./mall-develop/mall-swagger/target/mall-swagger.jar /mall/mall-swagger/mall-swagger.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18076
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-swagger-service", "-Dskywalking.agent.instance_name=mall-swagger-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-swagger.jar", "--spring.profiles.active=docker"]
