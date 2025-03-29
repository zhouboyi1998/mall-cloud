package com.cafe.infrastructure.rabbitmq.config;

import com.cafe.infrastructure.rabbitmq.property.RabbitMqProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.cafe.infrastructure.rabbitmq.constant.RabbitMqConstant.*;

@Configuration
@EnableRabbit
@EnableConfigurationProperties(value = {RabbitMqProperties.class})
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMqProperties rabbitMqProperties;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitMqProperties.getHost());
        factory.setPort(rabbitMqProperties.getPort());
        factory.setUsername(rabbitMqProperties.getUsername());
        factory.setPassword(rabbitMqProperties.getPassword());
        return factory;
    }

    /**
     * 创建普通队列
     */
    @Bean("queue")
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();

        //如果不配置这两个参数，那么在业务消息消费失败之后，是不会投递到死信交换机的。

        //当消息被拒绝或超时后，消息应该发送到哪个死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        //指定了消息发送到死信交换机时使用的路由键
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);

        return QueueBuilder.durable(QUEUE_NAME).withArguments(args).build();
    }

    /**
     * 定义交换机
     */
    @Bean("exchange")
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    /**
     * 声明业务队列和业务交换机的绑定关系
     */
    @Bean
    public Binding businessBinding(@Qualifier("queue") Queue queue,
                                   @Qualifier("exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    /**
     创建死信队列
     *
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    /**
     * 定义交换机
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明死信队列和死信交换机的绑定关系
     */
    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue queue,
                                     @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_ROUTING_KEY);
    }

}
