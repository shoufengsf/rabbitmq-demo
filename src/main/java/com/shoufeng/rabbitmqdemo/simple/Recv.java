package com.shoufeng.rabbitmqdemo.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author shoufeng
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        //连接工厂类
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //连接
        Connection connection = factory.newConnection();
        //通道
        Channel channel = connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.basicConsume(QUEUE_NAME, true, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String receiveMessage = new String(message.getBody(), StandardCharsets.UTF_8);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println(" [x] Received '" + receiveMessage + "'");
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
    }
}
