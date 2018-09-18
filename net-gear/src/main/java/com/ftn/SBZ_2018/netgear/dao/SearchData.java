package com.ftn.SBZ_2018.netgear.dao;

import java.util.List;

public class SearchData {

	private String productType;
	private List<String> manufactorers;
	
	public SearchData () {}

	public SearchData(String productType, List<String> manufactorers) {
		this.productType = productType;
		this.manufactorers = manufactorers;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<String> getManufactorers() {
		return manufactorers;
	}

	public void setManufactorers(List<String> manufactorers) {
		this.manufactorers = manufactorers;
	}

	@Override
	public String toString() {
		return "SearchData [productType=" + productType + ", manufactorers=" + manufactorers + "]";
	}
}
