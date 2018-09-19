package com.ftn.SBZ_2018.netgear.dao;

public class PreferenceDAO {

	private Long id;
	private Long userId;
	private String productType;
	private String preferenceType;
	private String value;
	private int productsCount;
	private Double percentage;
	
	public PreferenceDAO () {}

	public PreferenceDAO(Long id, Long userId, String productType, String preferenceType, String value,
			int productsCount, Double percentage) {
		this.id = id;
		this.userId = userId;
		this.productType = productType;
		this.preferenceType = preferenceType;
		this.value = value;
		this.productsCount = productsCount;
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPreferenceType() {
		return preferenceType;
	}

	public void setPreferenceType(String preferenceType) {
		this.preferenceType = preferenceType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getProductsCount() {
		return productsCount;
	}

	public void setProductsCount(int productsCount) {
		this.productsCount = productsCount;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "PreferenceDAO [id=" + id + ", userId=" + userId + ", productType=" + productType + ", preferenceType="
				+ preferenceType + ", value=" + value + ", productsCount=" + productsCount + ", percentage="
				+ percentage + "]";
	}
}
