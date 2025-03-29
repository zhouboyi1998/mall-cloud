package com.cafe.infrastructure.rabbitmq.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqProperties {

    private String host = "127.0.0.1";
    private int port = 5672;
    private String username = "mall";
    private String password = "mall";

}
