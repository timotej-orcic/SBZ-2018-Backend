package com.ftn.SBZ_2018.netgear.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class NetworkSystem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="netSys", fetch=FetchType.EAGER)
	private Set<Product> products;
	
	@ManyToOne
	@JsonBackReference(value="shopCart-networkSystem")
	private ShoppingCart shopCart;
	
	public NetworkSystem() {}

	public NetworkSystem(Long id, Set<Product> products, ShoppingCart shopCart) {
		this.id = id;
		this.products = products;
		this.shopCart = shopCart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public ShoppingCart getShopCart() {
		return shopCart;
	}

	public void setShopCart(ShoppingCart shopCart) {
		this.shopCart = shopCart;
	}

	@Override
	public String toString() {
		return "NetworkSystem [id=" + id + ", products=" + products + ", shopCart=" + shopCart + "]";
	}
}
