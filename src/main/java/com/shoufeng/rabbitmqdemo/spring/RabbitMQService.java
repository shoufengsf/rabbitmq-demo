package com.shoufeng.rabbitmqdemo.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void test(){}
}
