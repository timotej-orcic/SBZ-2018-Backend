package com.ftn.SBZ_2018.netgear.dao;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.Preference;
import com.ftn.SBZ_2018.netgear.model.Product;

public class ProductPreferencesDAO {

	private Product product;
	private List<Preference> preferences;
	
	public ProductPreferencesDAO () {}

	public ProductPreferencesDAO(Product product, List<Preference> preferences) {
		this.product = product;
		this.preferences = preferences;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

	@Override
	public String toString() {
		return "ProductPreferencesDAO [product=" + product + ", preferences=" + preferences + "]";
	}
}
