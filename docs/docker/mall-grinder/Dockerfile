FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-grinder
COPY ./mall-service/mall-grinder/target/mall-grinder.jar /mall/mall-grinder/mall-grinder.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18011
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-grinder-service", "-Dskywalking.agent.instance_name=mall-grinder-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-grinder.jar", "--spring.profiles.active=docker"]
