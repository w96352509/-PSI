package com.example.demo.test.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateEmployee {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void start() {
		Department t1 = departmentRepository.findById(7L).get();
		Department t2 = departmentRepository.findById(8L).get();
		
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		e1.setName("VIC");
		e2.setName("HSU");
		e1.setDepartment(t1);
		e2.setDepartment(t2);
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		
	}
}
