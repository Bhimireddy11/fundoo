package com.bridgelabz.fundoonotes.config;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.Response.MailObject;
@Component
public class RabbitMqSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("rmq.rube.exchange")
	private String exchange;
	
	@Value("rube.key")
	private String routingkey;	
	
	public void send(MailObject message) {
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
	}

}