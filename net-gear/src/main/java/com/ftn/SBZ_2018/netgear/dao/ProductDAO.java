package com.ftn.SBZ_2018.netgear.dao;

public class ProductDAO {

	private String name;
	private String manufactorer;
	private String description;
	private Double price;
	private int warrantyInMonths;
	
	public ProductDAO() {}

	public ProductDAO(String name, String manufactorer, String description, Double price, int warrantyInMonths) {
		this.name = name;
		this.manufactorer = manufactorer;
		this.description = description;
		this.price = price;
		this.warrantyInMonths = warrantyInMonths;
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

	@Override
	public String toString() {
		return "ProductDAO [name=" + name + ", manufactorer=" + manufactorer + ", description=" + description
				+ ", price=" + price + ", warrantyInMonths=" + warrantyInMonths +"]";
	}
}
