package com.ftn.SBZ_2018.netgear.dao;

import com.ftn.SBZ_2018.netgear.model.Product;

public class NetworkSystemItemDAO {

	private Product product;
	private int quantity;
	
	public NetworkSystemItemDAO () {}

	public NetworkSystemItemDAO(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "NetworkSystemItemDAO [product=" + product + ", quantity=" + quantity + "]";
	}
}
