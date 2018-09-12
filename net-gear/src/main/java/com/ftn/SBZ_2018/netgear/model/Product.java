package com.ftn.SBZ_2018.netgear.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String type;
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	private UploadedImage base64Image;
	
	@ManyToOne
	@JsonBackReference(value="netSys")
	private NetworkSystem netSys;
	
	@ManyToOne
	@JsonBackReference(value="shopCart-product")
	private ShoppingCart shopCart;
	
	public Product() {}

	public Product(Long id, String type, String manufactorer, String description,
			Double price, int warrantyInMonths, UploadedImage base64Image, 
			NetworkSystem netSys, ShoppingCart shopCart) {
		this.id = id;
		this.type = type;
		this.manufactorer = manufactorer;
		this.description = description;
		this.price = price;
		this.warrantyInMonths = warrantyInMonths;
		this.base64Image = base64Image;
		this.netSys = netSys;
		this.shopCart = shopCart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public UploadedImage getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(UploadedImage base64Image) {
		this.base64Image = base64Image;
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
		return "Product [id=" + id + ", type=" + type + ", manufactorer=" + manufactorer + ", description="
				+ description + ", price=" + price + ", warrantyInMonths=" + warrantyInMonths
				+ "image"+ base64Image.getName() + ", netSys=" + netSys + ", shopCart=" + shopCart + "]";
	}
}
