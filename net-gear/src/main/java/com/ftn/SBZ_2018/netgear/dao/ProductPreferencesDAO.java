package com.ftn.SBZ_2018.netgear.dao;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.Product;

public class ProductPreferencesDAO {

	private List<Product> products;
	private List<PreferenceDAO> preferences;
	
	public ProductPreferencesDAO () {}

	public ProductPreferencesDAO(List<Product> products, List<PreferenceDAO> preferences) {
		this.products = products;
		this.preferences = preferences;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<PreferenceDAO> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<PreferenceDAO> preferences) {
		this.preferences = preferences;
	}

	@Override
	public String toString() {
		return "ProductPreferencesDAO [products=" + products + ", preferences=" + preferences + "]";
	}
}
