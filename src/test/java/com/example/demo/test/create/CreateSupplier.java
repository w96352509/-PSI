package com.example.demo.test.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateSupplier {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Test
	public void start() {
		Supplier supplier1 = new Supplier();
		supplier1.setName("迷克夏");
		Supplier supplier2 = new Supplier();
		supplier2.setName("可不可");
		supplierRepository.save(supplier1);
		supplierRepository.save(supplier2);
	}
}
