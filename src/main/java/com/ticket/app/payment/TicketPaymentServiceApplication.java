package com.ticket.app.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TicketPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketPaymentServiceApplication.class, args);
	}

}
