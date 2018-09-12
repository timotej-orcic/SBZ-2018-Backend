package com.ftn.SBZ_2018.netgear.dao;

public class SearchProduct {

	private String productType;	
	private String manufacorer;	
	private Double priceMin;	
	private Double priceMax;	
	private int warrantyInMonthsMin;	
	private boolean includeUserPreferences;
	
	public SearchProduct() {}

	public SearchProduct(String productType, String manufacorer, Double priceMin, Double priceMax,
			int warrantyInMonthsMin, boolean includeUserPreferences) {
		this.productType = productType;
		this.manufacorer = manufacorer;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.warrantyInMonthsMin = warrantyInMonthsMin;
		this.includeUserPreferences = includeUserPreferences;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getManufacorer() {
		return manufacorer;
	}

	public void setManufacorer(String manufacorer) {
		this.manufacorer = manufacorer;
	}

	public Double getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Double priceMin) {
		this.priceMin = priceMin;
	}

	public Double getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Double priceMax) {
		this.priceMax = priceMax;
	}

	public int getWarrantyInMonthsMin() {
		return warrantyInMonthsMin;
	}

	public void setWarrantyInMonthsMin(int warrantyInMonthsMin) {
		this.warrantyInMonthsMin = warrantyInMonthsMin;
	}

	public boolean isIncludeUserPreferences() {
		return includeUserPreferences;
	}

	public void setIncludeUserPreferences(boolean includeUserPreferences) {
		this.includeUserPreferences = includeUserPreferences;
	}

	@Override
	public String toString() {
		return "SearchProduct [productType=" + productType + ", manufacorer=" + manufacorer + ", priceMin=" + priceMin
				+ ", priceMax=" + priceMax + ", warrantyInMonthsMin=" + warrantyInMonthsMin
				+ ", includeUserPreferences=" + includeUserPreferences + "]";
	}
}
