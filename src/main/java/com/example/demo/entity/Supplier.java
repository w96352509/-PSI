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
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer companyphone;
	
	@Column
	private Integer companyFax;
	
	@Column
	private String contactperson;
	
	@Column
	private String companyaddress;
	
	@Column
	private String remarks;
	
	@OneToMany(mappedBy = "supplier")
	@OrderBy("id ASC")
	private Set<Purchase> purchases = new LinkedHashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompanyphone() {
		return companyphone;
	}

	public void setCompanyphone(Integer companyphone) {
		this.companyphone = companyphone;
	}

	public Integer getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(Integer companyFax) {
		this.companyFax = companyFax;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	 
	
	
}
