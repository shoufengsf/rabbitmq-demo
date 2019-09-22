package com.shoufeng.rabbitmqdemo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author shoufeng
 */
public class ConnectionUtil {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    public static Connection getConnection() throws IOException, TimeoutException {
        //连接工厂
        connectionFactory.setHost("127.0.0.1");
        //设置服务器地址
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/second_kill");
        connectionFactory.setUsername("second_kill_user");
        connectionFactory.setPassword("123456");
        return connectionFactory.newConnection();
    }
}
