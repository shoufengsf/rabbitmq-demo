package com.shoufeng.rabbitmqdemo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author shoufeng
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //连接工厂类
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (//连接
             Connection connection = factory.newConnection();
             //创建通道
             Channel channel = connection.createChannel()) {
            //队列声明：
            //queue：队列名
            //durable：如果我们声明一个持久队列(队列将在服务器重启后继续存在)，则为true
            //exclusive：如果我们声明一个独占队列(仅限于此连接)，则为true
            //autoDelete：如果我们声明一个自动删除队列(服务器将在不再使用时删除它)，则为true
            //arguments：队列的其他属性(构造参数)
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //消息内容
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
