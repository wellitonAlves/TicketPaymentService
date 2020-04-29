package com.ticket.app.payment.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

@RestController
@RequestMapping("/product")
public class PaymentController {
	
	
	 private final static String QUEUE_NAME = "hello";
	
	@GetMapping("/status/check")
	public String status() {		
		return "working";
	}
	
	
	@GetMapping("/producer")
	public String producer() {

		try {
			testProducer();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
		
		return "producer";
	}
	
	
	@GetMapping("/consumer")
	public String consumer() {

		try {
			testConsumer();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
		
		return "consumer";
	}
	
	
	public void testProducer() throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
		     Channel channel = connection.createChannel()) {
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello World!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");

		}
	}
	
	public void testConsumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}

}
