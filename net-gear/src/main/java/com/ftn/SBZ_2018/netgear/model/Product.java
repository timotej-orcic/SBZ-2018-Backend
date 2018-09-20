package com.ftn.SBZ_2018.netgear.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
	
	@Column(nullable=false, length=50)
	private int specialLabel;
	
	@Column(nullable=false)
	@Min(0)
	private Double price;
	
	@Column(nullable=false)
	@Min(0)
	@Max(60)
	private int warrantyInMonths;
	
	@Column(nullable=false)
	@Min(0)
	private int lagerQuantity;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UploadedImage base64Image;
	
	public Product() {}

	public Product(Long id, String type, String manufactorer, String description, int specialLabel,
			Double price, int warrantyInMonths, int lagerQuantity, UploadedImage base64Image) {
		this.id = id;
		this.type = type;
		this.manufactorer = manufactorer;
		this.description = description;
		this.specialLabel = specialLabel;
		this.price = price;
		this.warrantyInMonths = warrantyInMonths;
		this.lagerQuantity = lagerQuantity;
		this.base64Image = base64Image;
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

	public int getSpecialLabel() {
		return specialLabel;
	}

	public void setSpecialLabel(int specialLabel) {
		this.specialLabel = specialLabel;
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

	public int getLagerQuantity() {
		return lagerQuantity;
	}

	public void setLagerQuantity(int lagerQuantity) {
		this.lagerQuantity = lagerQuantity;
	}

	public UploadedImage getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(UploadedImage base64Image) {
		this.base64Image = base64Image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + ", manufactorer=" + manufactorer + ", description="
				+ description + ", specialLabel=" + specialLabel + ", price=" + price + ", warrantyInMonths="
				+ warrantyInMonths + ", lagerQuantity=" + lagerQuantity + ", base64Image=" + base64Image + "]";
	}
}
