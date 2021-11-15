package com.example.demo.test.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateProduct {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void start() {
		Product product1 = new Product();
		Product product2 = new Product();
		product1.setName("紅茶");
		product2.setName("奶茶");
		product1.setPrice(30);
		product2.setPrice(40);
		product1.setCost(3);
		product2.setCost(4);
		
		productRepository.save(product1);
		productRepository.save(product2);
		
	}
}
