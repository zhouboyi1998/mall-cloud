FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-openapicenter
COPY ./mall-center/mall-openapicenter/target/mall-openapicenter.jar /mall/mall-openapicenter/mall-openapicenter.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18062
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-openapicenter-service", "-Dskywalking.agent.instance_name=mall-openapicenter-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-openapicenter.jar", "--spring.profiles.active=docker"]
