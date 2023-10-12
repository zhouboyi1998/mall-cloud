FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-solr
COPY ./mall-middleware/mall-solr/mall-solr-core/target/mall-solr.jar /mall/mall-solr/mall-solr.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18094
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-solr-service", "-Dskywalking.agent.instance_name=mall-solr-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-solr.jar", "--spring.profiles.active=docker"]
