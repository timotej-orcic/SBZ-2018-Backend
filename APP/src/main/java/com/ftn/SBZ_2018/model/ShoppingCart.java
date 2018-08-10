package com.ftn.SBZ_2018.model;

import java.io.Serializable;
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
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="shoppingcart", fetch=FetchType.EAGER)
	private Set<Product> productsCart;
	
	@OneToMany(mappedBy="shoppingcart", fetch=FetchType.EAGER)
	private Set<NetworkSystem> netSystemsCart;
	
	@ManyToOne
	@JsonBackReference
	private User user;
	
	public ShoppingCart() {}

	public ShoppingCart(Long id, Set<Product> productsCart, Set<NetworkSystem> netSystemsCart, User user) {
		super();
		this.id = id;
		this.productsCart = productsCart;
		this.netSystemsCart = netSystemsCart;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Product> getProductsCart() {
		return productsCart;
	}

	public void setProductsCart(Set<Product> productsCart) {
		this.productsCart = productsCart;
	}

	public Set<NetworkSystem> getNetSystemsCart() {
		return netSystemsCart;
	}

	public void setNetSystemsCart(Set<NetworkSystem> netSystemsCart) {
		this.netSystemsCart = netSystemsCart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
