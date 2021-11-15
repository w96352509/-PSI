package com.example.demo.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@OneToMany(mappedBy = "customer")
	@OrderBy("id ASC")
	private Set<Order> orders = new LinkedHashSet<>();
    
	
	//自帶參數--------------------------------------------------------------------
	@Column
	private String name; //名字
	@Column
	private Integer number; //電話
	@Column
	private Integer homenumber; //家裡電話
	@Column
	private Integer onenumber; // 統一編號
	@Column
	private String  address;  //地址
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getHomenumber() {
		return homenumber;
	}
	public void setHomenumber(Integer homenumber) {
		this.homenumber = homenumber;
	}
	public Integer getOnenumber() {
		return onenumber;
	}
	public void setOnenumber(Integer onenumber) {
		this.onenumber = onenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
