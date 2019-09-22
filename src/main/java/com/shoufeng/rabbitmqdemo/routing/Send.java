package com.shoufeng.rabbitmqdemo.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shoufeng.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 路由匹配
 * @author shoufeng
 */
public class Send {
    private final static String EXCHANGE_NAME = "exchange_routing_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String message = "发出的一条消息";
        List<String> keyList = new ArrayList<String>(){
            {
                add("routingkey_111");
                add("routingkey_222");
                add("routingkey_2222");
            }
        };
        for (String s : keyList) {
            channel.basicPublish(EXCHANGE_NAME,s,null,message.getBytes(StandardCharsets.UTF_8));
        }
        channel.close();
        connection.close();
    }
}
