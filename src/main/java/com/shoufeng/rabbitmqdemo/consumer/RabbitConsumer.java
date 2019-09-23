package com.shoufeng.rabbitmqdemo.consumer;

/**
 * @author shoufeng
 */
public class RabbitConsumer {
    public void listen(String message){
        System.out.println("消费者得到的消息：" + message);
    }
}
