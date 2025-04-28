FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-captcha
COPY ./mall-service/mall-captcha/target/mall-captcha.jar /mall/mall-captcha/mall-captcha.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18077
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-captcha-service", "-Dskywalking.agent.instance_name=mall-captcha-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-captcha.jar", "--spring.profiles.active=docker"]
