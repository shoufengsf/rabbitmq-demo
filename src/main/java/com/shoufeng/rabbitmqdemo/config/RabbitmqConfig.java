package com.shoufeng.rabbitmqdemo.config;

import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通用化 Rabbitmq 配置
 */
@Configuration
public class RabbitmqConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    //队列
    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true, false, false);
    }

    //交换机
    @Bean
    public TopicExchange myTopicExchange() {
        return new TopicExchange("myTopicExchange", true, false);
    }

    //绑定
    @Bean
    public Binding myBinding(){
        return BindingBuilder.bind(myQueue()).to(myTopicExchange()).with("myRoutingKey");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                LOGGER.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                LOGGER.warn("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }
}






























































































