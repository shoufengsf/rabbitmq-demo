package com.shoufeng.rabbitmqdemo;

import com.shoufeng.rabbitmqdemo.producer.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ImportResource(value = {"classpath*:spring/rabbitmq-context.xml"})
public class RabbitmqDemoApplicationTests {

    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void contextLoads() throws Exception {
        rabbitSender.sendOrder("你好世界");
    }

}
