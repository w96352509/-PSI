package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@RequestMapping("/")
	public String index(Model model , 
	@RequestParam(name="supplier_id" , required = false) Long supplier_id) {
	Purchase purchase = new Purchase();
	if(supplier_id != null){
		Supplier supplier = supplierRepository.findById(supplier_id).get();
		purchase.setSupplier(supplier);
		model.addAttribute("purchases", purchaseRepository.findBySupplier(supplier));
	     }else{
	      List<Purchase> purchases = purchaseRepository.findAll();
	      model.addAttribute("purchases", purchases);
	     }	
	 model.addAttribute("purchase", purchase);
	 //下拉選單
	 model.addAttribute("employees", employeeRepository.findAll());
	 model.addAttribute("suppliers", supplierRepository.findAll());
	 return "purchase" ; 
	}
	
	@RequestMapping("/add")
	public String add(Purchase purchase) {
	    Supplier supplier = supplierRepository.findById(purchase.getSupplier().getId()).get(); 
		purchase.setSupplier(supplier);
		Employee employee = employeeRepository.findById(purchase.getEmployee().getId()).get();
		purchase.setEmployee(employee);
		purchaseRepository.save(purchase);
		return "redirect:/purchase/?supplier_id="+supplier.getId();
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model , @PathVariable("id") Long id) {
		 Purchase purchase=purchaseRepository.findById(id).get();
		 model.addAttribute("purchase" , purchase);
		 //下拉選單
		 model.addAttribute("employees", employeeRepository.findAll());
		 model.addAttribute("suppliers", supplierRepository.findAll());
		return "purchase-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(Purchase purchase ,@PathVariable("id") Long id) {
		Supplier supplier = supplierRepository.findById(purchase.getSupplier().getId()).get();
		purchase.setId(id);
		purchase.setSupplier(supplier);
		purchaseRepository.save(purchase);		
		return "redirect:/purchase/?supplier_id=" + supplier.getId();
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Purchase purchase = purchaseRepository.findById(id).get();
		Supplier supplier = supplierRepository.findById(purchase.getSupplier().getId()).get();
		purchaseRepository.deleteById(id);
		
		return "redirect:/purchase/?supplier_id="+supplier.getId();
	}
	
}
