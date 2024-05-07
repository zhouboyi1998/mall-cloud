FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-qiniu
COPY ./mall-middleware/mall-qiniu/target/mall-qiniu.jar /mall/mall-qiniu/mall-qiniu.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18095
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-qiniu-service", "-Dskywalking.agent.instance_name=mall-qiniu-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-qiniu.jar", "--spring.profiles.active=docker"]
