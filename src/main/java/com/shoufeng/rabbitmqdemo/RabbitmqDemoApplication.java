package com.shoufeng.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource(value = {"classpath*:spring/rabbitmq-context.xml"})
public class RabbitmqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }

}
