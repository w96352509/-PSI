package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	
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
// PurchaseItem-------------------------------------------------
	
	@RequestMapping("/{pid}/view/item")
	public String viewitem(Model model , @PathVariable("pid") Long pid) {
		PurchaseItem purchaseItem = new PurchaseItem();
		Purchase purchase = purchaseRepository.findById(pid).get();
		model.addAttribute("purchaseItem", purchaseItem);
		model.addAttribute("purchase", purchase);
		model.addAttribute("products", productRepository.findAll());
		return "purchaseItem";
	}
	
	@RequestMapping("/{oid}/add/item")
	public String add(@PathVariable("oid") Long oid , PurchaseItem purchaseItem) {
		Purchase purchase = purchaseRepository.findById(oid).get();
		purchaseItem.setPurchase(purchase);
		purchaseItemRepository.save(purchaseItem);
		return "redirect:/purchase/"+oid +"/view/item";
	}
	
	@RequestMapping("/{oid}/edit/item/{iid}")
	public String editItem(@PathVariable("oid") Long oid , @PathVariable("iid") Long iid ,Model model) {
		PurchaseItem purchaseItem = purchaseItemRepository.findById(iid).get();
		Purchase purchase = purchaseRepository.findById(oid).get();
		model.addAttribute("purchaseItem", purchaseItem);
		model.addAttribute("purchase", purchase);
		model.addAttribute("products", productRepository.findAll());
		return "purchaseItem"; 
		//要讓資料上form所以不能return "redirect:/purchase/"+oid +"/view/item";
	}
	
	@RequestMapping("/{oid}/delete/item/{iid}")
	public String delete(@PathVariable("oid") Long oid , @PathVariable("iid") Long iid ,Model model) {
		purchaseItemRepository.deleteById(iid);
		return "redirect:/purchase/"+oid +"/view/item";
		
	}
	
}
