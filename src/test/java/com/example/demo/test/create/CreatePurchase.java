package com.example.demo.test.create;



import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreatePurchase {

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	PurchaseItemRepository purchaseItemRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Test
	void start() {
		// 資料預備
		Supplier s1 = supplierRepository.findById(6L).get();// 供應商
		Employee e2 = employeeRepository.findById(3L).get();// 服務員工
		Product p1 = productRepository.findById(4L).get();// 購買商品
		

		// 建立採購單
		Purchase purchase = new Purchase();
		purchase.setDate(new Date());

		// 配置採購單的關聯(看圖)
		purchase.setSupplier(s1);
		purchase.setEmployee(e2);
		// --------------------

		// 建立採購單細目1
	PurchaseItem item1 = new PurchaseItem();
		item1.setAmount(3000); //數量
		// 配置採購單細目關聯
		item1.setPurchase(purchase);
		item1.setProduct(p1);

		
		
		//保存
	    purchaseRepository.save(purchase);
		purchaseItemRepository.save(item1);
		

	}

}
