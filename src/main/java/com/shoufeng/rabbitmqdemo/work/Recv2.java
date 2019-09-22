package com.shoufeng.rabbitmqdemo.work;

import com.rabbitmq.client.*;
import com.shoufeng.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shoufeng
 */
public class Recv2 {
    private final static String QUEUE_NAME = "work_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //多劳多得需要加上这行代码
        channel.basicQos(1);
        AtomicInteger count = new AtomicInteger();
        channel.basicConsume(QUEUE_NAME, true, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String receiveMessage = new String(message.getBody(), StandardCharsets.UTF_8);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println(" [x] Received '" + receiveMessage + "'");
                System.out.println("总共接受了：" + count.incrementAndGet() + "条数据");
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
    }
}
