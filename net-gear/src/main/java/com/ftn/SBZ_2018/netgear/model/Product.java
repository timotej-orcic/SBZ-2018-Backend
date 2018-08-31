package com.ftn.SBZ_2018.netgear.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String name;
	
	@Column(nullable=false, length=50)
	private String manufactorer;
	
	@Column(nullable=true, length=300)
	private String description;
	
	@Column(nullable=false)
	@Min(0)
	private Double price;
	
	@Column(nullable=false)
	@Min(0)
	private int warrantyInMonths;
	
	@ManyToOne
	@JsonBackReference
	private NetworkSystem netSys;
	
	@ManyToOne
	@JsonBackReference
	private ShoppingCart shopCart;
	
	public Product() {}

	public Product(Long id, String name, String manufactorer, String description,
			Double price, int warrantyInMonths, NetworkSystem netSys, ShoppingCart shopCart) {
		this.id = id;
		this.name = name;
		this.manufactorer = manufactorer;
		this.description = description;
		this.price = price;
		this.warrantyInMonths = warrantyInMonths;
		this.netSys = netSys;
		this.shopCart = shopCart;
	}

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

	public String getManufactorer() {
		return manufactorer;
	}

	public void setManufactorer(String manufactorer) {
		this.manufactorer = manufactorer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getWarrantyInMonths() {
		return warrantyInMonths;
	}

	public void setWarrantyInMonths(int warrantyInMonths) {
		this.warrantyInMonths = warrantyInMonths;
	}

	public NetworkSystem getNetSys() {
		return netSys;
	}

	public void setNetSys(NetworkSystem netSys) {
		this.netSys = netSys;
	}

	public ShoppingCart getShopCart() {
		return shopCart;
	}

	public void setShopCart(ShoppingCart shopCart) {
		this.shopCart = shopCart;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", manufactorer=" + manufactorer + ", description="
				+ description + ", price=" + price + ", warrantyInMonths=" + warrantyInMonths + ", netSys=" + netSys
				+ ", shopCart=" + shopCart + "]";
	}
}
