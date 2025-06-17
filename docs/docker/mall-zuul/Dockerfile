FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-zuul
COPY ./mall-service/mall-zuul/target/mall-zuul.jar /mall/mall-zuul/mall-zuul.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 8040
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-zuul-service", "-Dskywalking.agent.instance_name=mall-zuul-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-zuul.jar", "--spring.profiles.active=docker"]
