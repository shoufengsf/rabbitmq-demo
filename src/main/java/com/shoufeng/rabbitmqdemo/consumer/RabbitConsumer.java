package com.shoufeng.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author shoufeng
 */

@Component
public class RabbitConsumer {
    public void listen(String message){
        System.out.println("消费者得到的消息：" + message);
    }

    @RabbitListener(queues = "myQueue")
    public void listenTest(String message) {
        System.out.println("接收到消息：" + message);
    }
}
