package com.shoufeng.rabbitmqdemo.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shoufeng.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * topic通配符匹配
 * @author shoufeng
 */
public class Send {
    private final static String EXCHANGE_NAME = "exchange_test_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String message = "topic一条消息";
        channel.basicPublish(EXCHANGE_NAME,"routing2.22",null,message.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME,"routing2.22.xxx",null,message.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME,"routing2.233.xx.yy",null,message.getBytes(StandardCharsets.UTF_8));
        channel.close();
        connection.close();
    }
}
