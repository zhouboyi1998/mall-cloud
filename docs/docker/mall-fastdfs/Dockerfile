FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-fastdfs
COPY ./mall-middleware/mall-fastdfs/mall-fastdfs-core/target/mall-fastdfs.jar /mall/mall-fastdfs/mall-fastdfs.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18092
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-fastdfs-service", "-Dskywalking.agent.instance_name=mall-fastdfs-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-fastdfs.jar", "--spring.profiles.active=docker"]
