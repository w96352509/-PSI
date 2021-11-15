package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {

	@RequestMapping("/")
	public String index() {
	
		return "orderItem" ; 
	}
	
}
