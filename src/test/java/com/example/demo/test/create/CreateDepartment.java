package com.example.demo.test.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Department;
import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateDepartment {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void start() {
		Department department1 = new Department();
		Department department2 = new Department();
		department1.setName("採購部");
		department2.setName("拍賣部");
		departmentRepository.save(department1);
		departmentRepository.save(department2);
	}
}
