package com.shoufeng.rabbitmqdemo.work;

import com.rabbitmq.client.*;
import com.shoufeng.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shoufeng
 */
public class Recv1 {
    private final static String QUEUE_NAME = "work_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        AtomicInteger count = new AtomicInteger();
        //多劳多得需要加上这行代码
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println(" [x] Received '" + message + "'");
                System.out.println("总共接受了：" + count.incrementAndGet() + "条数据");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false,defaultConsumer);
//        channel.basicConsume(QUEUE_NAME, false, new DeliverCallback() {
//            @Override
//            public void handle(String consumerTag, Delivery message) throws IOException {
//                String receiveMessage = new String(message.getBody(), StandardCharsets.UTF_8);
//                System.out.println("consumerTag: " + consumerTag);
//                System.out.println(" [x] Received '" + receiveMessage + "'");
//                System.out.println("总共接受了：" + count.incrementAndGet() + "条数据");
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new CancelCallback() {
//            @Override
//            public void handle(String consumerTag) throws IOException {
//
//            }
//        });
    }
}
