package com.ftn.SBZ_2018.netgear.dao;

public class ShoppingCartItemDAO {

	private Long productId;
	private int quantity;
	private Double price;
	
	public ShoppingCartItemDAO() {}

	public ShoppingCartItemDAO(Long productId, int quantity, Double price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShoppingCartItemDAO [productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
