package com.ftn.SBZ_2018.netgear.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ShoppingCartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JsonBackReference(value="shopingCartItem")
	private Product product;
	
	@Min(1)
	@Column(nullable=false)
	private int quantity;
	
	@ManyToOne
	@JsonBackReference(value="discount")
	private Discount discount;
	
	@Min(0)
	@Column(nullable=false)
	private Double price;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name="shoppingCart_id", nullable=false, insertable=true)
    private ShoppingCart shoppingCart;
	
	public ShoppingCartItem() {}

	public ShoppingCartItem(Long id, Product product, @Min(1) int quantity,
			Discount discount, @Min(0) Double price, ShoppingCart shoppingCart) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.discount = discount;
		this.price = price;
		this.shoppingCart = shoppingCart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [id=" + id + ", product=" + product + ", quantity=" + quantity + ", discount="
				+ discount + ", price=" + price + ", shoppingCart=" + shoppingCart + "]";
	}
}
