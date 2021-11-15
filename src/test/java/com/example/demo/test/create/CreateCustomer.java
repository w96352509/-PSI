package com.example.demo.test.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootTest
public class CreateCustomer {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void start() {
		Customer customer1 = new Customer();
		customer1.setName("CC1");
		Customer customer2 = new Customer();
		customer2.setName("CC2");
		
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		
	}
}
