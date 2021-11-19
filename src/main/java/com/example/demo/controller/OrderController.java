package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.validator.InventoryValidator;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
    private InventoryValidator inventoryValidator;
	
	
	@RequestMapping("/")
	public String index(Model model , @RequestParam(name="customer_id" ,required = false) Long customer_id) {
	    Order order = new Order();
	    //判斷列出那些order
	   if(customer_id == null) {
		   //全
	          model.addAttribute("orders", orderRepository.findAll());
	    }else {
	    	//從customer中帶入
	    	Customer customer = customerRepository.findById(customer_id).get();
	    	//連結此ID的customer跟order連結 EX客戶:跳出為這訂單的客戶
	    	//之後的order就有customer資料在當中
		    order.setCustomer(customer);
		    //表單要顯示的客戶來自哪個表單中查詢
		    model.addAttribute("orders", orderRepository.findByCustomer(customer));
		}
	    model.addAttribute("order", order);
	    //下拉選單部分
	    model.addAttribute("employees" , employeeRepository.findAll());
	    model.addAttribute("customers",customerRepository.findAll());
	    return "order" ; 
	}
	
	@RequestMapping("/add")
	public String create(Order order) {
		//讓order:${orders} 的 order 有明確的指標
		//return "redirect:/order/?customer_id="+ customer.getId();回到該使用者介面
		Customer customer = customerRepository.findById(order.getCustomer().getId()).get();
	    orderRepository.save(order);
		return "redirect:/order/?customer_id="+ customer.getId();
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id , Model model) {
		Order order = orderRepository.findById(id).get();
		//帶上要更改的
		model.addAttribute("order" , order);
		//下拉選單
		model.addAttribute("employees" , employeeRepository.findAll());
	    model.addAttribute("customers",customerRepository.findAll());
		return "order-update" ;
	}
	
	@RequestMapping("/update/{id}")
	public String update(Order order ,@PathVariable("id") Long id) {
		Customer customer = customerRepository.findById(order.getCustomer().getId()).get();
		order.setCustomer(customer);
		Employee employee = employeeRepository.findById(order.getEmployee().getId()).get();
		order.setEmployee(employee);
		order.setId(id);
		orderRepository.save(order);
		return "redirect:/order/?customer_id="+ customer.getId();
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Order order =orderRepository.findById(id).get();
		Customer customer =customerRepository.findById(order.getCustomer().getId()).get();
		orderRepository.deleteById(id);
		return "redirect:/order/?customer_id="+ customer.getId();
	}
//------------------------------------------------------------------------------------------	
//orderItem部分
	@RequestMapping("/{oid}/view/item")
	public String viewItem(Model model, @PathVariable("oid") Long oid) {
		Order order = orderRepository.findById(oid).get();
		OrderItem orderItem = new OrderItem();
		model.addAttribute("order", order);
		model.addAttribute("orderItem", orderItem);
		model.addAttribute("products", productRepository.findAll());
		return "orderItem";
	}
	
	@RequestMapping("/{oid}/add/item")
	// Model model, BindingResult result 注意宣告的參數順序
	public String addItem(OrderItem orderItem, Model model, 
			 BindingResult result
			,@PathVariable("oid") Long oid) {
		//驗證資料 
		inventoryValidator.validate(orderItem, result);
		if (result.hasErrors()) {
			Order order = orderRepository.findById(oid).get();
			model.addAttribute("order", order);
			model.addAttribute("orderItem", orderItem);
			model.addAttribute("products", productRepository.findAll());
			return "orderItem";
		}
		 //找到帶入id的order後,由多方進行維護關聯
         Order order = orderRepository.findById(oid).get();
		 //將帶入id的order的資料帶到orderItem裡
		orderItem.setOrder(order);
		orderItemRepository.save(orderItem);
		return "redirect:/order/" + oid + "/view/item";
	}
	
	
	
	   
}
