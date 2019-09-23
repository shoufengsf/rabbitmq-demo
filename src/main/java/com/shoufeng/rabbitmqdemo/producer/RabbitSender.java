package com.shoufeng.rabbitmqdemo.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitSender {

	//自动注入RabbitTemplate模板类
	@Autowired
	private RabbitTemplate rabbitTemplate;

	//发送消息方法调用
	public void sendOrder(String message) throws Exception {
		rabbitTemplate.convertAndSend(message);
	}
	
}
