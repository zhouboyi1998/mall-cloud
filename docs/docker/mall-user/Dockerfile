FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-user
COPY ./mall-service/mall-user/target/mall-user.jar /mall/mall-user/mall-user.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18073
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-user-service", "-Dskywalking.agent.instance_name=mall-user-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-user.jar", "--spring.profiles.active=docker"]
