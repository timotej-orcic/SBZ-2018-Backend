package com.ftn.SBZ_2018.netgear.dao;

public class ProductDAO {

	private Long id;
	private String type;
	private String manufactorer;
	private String description;
	private Double price;
	private int warrantyInMonths;
	
	public ProductDAO() {}

	public ProductDAO(Long id, String type, String manufactorer, String description, Double price, int warrantyInMonths) {
		this.id = id;
		this.type = type;
		this.manufactorer = manufactorer;
		this.description = description;
		this.price = price;
		this.warrantyInMonths = warrantyInMonths;
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

	@Override
	public String toString() {
		return "ProductDAO [id= " +  id + ", type=" + type + ", manufactorer=" + manufactorer + ", description=" + description
				+ ", price=" + price + ", warrantyInMonths=" + warrantyInMonths +"]";
	}
}
