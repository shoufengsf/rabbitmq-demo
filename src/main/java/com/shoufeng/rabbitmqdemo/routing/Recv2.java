package com.shoufeng.rabbitmqdemo.routing;

import com.rabbitmq.client.*;
import com.shoufeng.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author shoufeng
 */
public class Recv2 {
    private final static String EXCHANGE_NAME = "exchange_routing_test";
    private final static String QUEUE_NAME = "queue_work_test_222";
    private final static String ROUTING_KEY1 = "routingkey_222";
    private final static String ROUTING_KEY2 = "routingkey_2222";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY1);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY2);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("接收到来自交换器" + EXCHANGE_NAME + "队列" + QUEUE_NAME + "路由" + envelope.getRoutingKey() + "的消息：" + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
