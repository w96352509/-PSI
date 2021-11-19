package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/")
	public String index(Model model , 
	    @RequestParam(name="orderItem_id" ,required = false)Long orderItem_id ) {
	    Product product = new Product();
	    List<Product> products = productRepository.findAll();
		model.addAttribute("product",product);
		model.addAttribute("products", products);
		return "product" ; 
	}
	
	@RequestMapping("/create")
	public String create(Product product) {
		productRepository.save(product);
		return "redirect:/product/";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Product product = productRepository.findById(id).get();
		model.addAttribute("product", product);
		return "product-update";
	}
	
	@RequestMapping("/update/{id}")
	public String edit(@PathVariable("id") Long id, Product product) {
        product.setId(id);
        productRepository.save(product); 
		return "redirect:/product/";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		productRepository.deleteById(id);
		return "redirect:/product/";
	}
}
