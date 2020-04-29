package com.ticket.app.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.app.payment.model.User;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	public static final String TOPIC = "testTopic_json2";
	
	@GetMapping("/publish/{name}")
	public String status(@PathVariable("name") String name) {	
		
		kafkaTemplate.send(TOPIC,new User(name, "20", "adress"));
		
		return "working";
	}
	
	@KafkaListener(topics = "testTopicString", groupId = "group_id")
	public void consume(String message) {
		System.out.println("Consumed message: " + message);
	}
	
	
	@KafkaListener(topics = "testTopic_json2", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
	public void consumeJson(User user) {
		System.out.println("Consumed Json user: " + user);
	}

}
