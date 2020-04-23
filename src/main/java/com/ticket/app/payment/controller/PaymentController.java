package com.ticket.app.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class PaymentController {
	
	@GetMapping("/status/check")
	public String status() {
		return "working";
	}

}
