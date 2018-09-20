package com.ftn.SBZ_2018.netgear.dao;

public class ProductDAO {

	private Long id;
	private String type;
	private String manufactorer;
	private String description;
	private int specialLabel;
	private Double price;
	private int warrantyInMonths;
	private int lagerQuantity;
	
	public ProductDAO() {}

	public ProductDAO(Long id, String type, String manufactorer, String description,
			int specialLabel, Double price, int warrantyInMonths, int lagerQuantity) {
		this.id = id;
		this.type = type;
		this.manufactorer = manufactorer;
		this.description = description;
		this.specialLabel = specialLabel;
		this.price = price;
		this.warrantyInMonths = warrantyInMonths;
		this.lagerQuantity = lagerQuantity;
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

	@Override
	public String toString() {
		return "ProductDAO [id=" + id + ", type=" + type + ", manufactorer=" + manufactorer + ", description="
				+ description + ", specialLabel=" + specialLabel + ", price=" + price + ", warrantyInMonths="
				+ warrantyInMonths + ", lagerQuantity=" + lagerQuantity + "]";
	}
}
