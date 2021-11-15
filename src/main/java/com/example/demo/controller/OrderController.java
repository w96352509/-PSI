package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderRepository;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping("/")
	public String index(Model model , @RequestParam(name="customer_id" ,required = false) Long customer_id) {
	    Order order = new Order();
	    Customer customer =null;
	    if(customer_id == null) {
	          model.addAttribute("orders", orderRepository.findAll());
	    }else {
	    	customer = customerRepository.findById(customer_id).get();
		    order.setCustomer(customer);
		    model.addAttribute("orders", orderRepository.findByCustomer(customer));
		}
	    model.addAttribute("order", order);
	    //下拉選單部分
	    model.addAttribute("employees" , employeeRepository.findAll());
	    model.addAttribute("customers",customerRepository.findAll());
	    return "order" ; 
	}
	
}
