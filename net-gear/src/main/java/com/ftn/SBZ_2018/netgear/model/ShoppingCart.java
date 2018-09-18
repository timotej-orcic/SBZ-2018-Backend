package com.ftn.SBZ_2018.netgear.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private Date salesDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy="shoppingCart", fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<ShoppingCartItem> items;
	
	@ManyToOne
	@JsonBackReference(value="user")
	private User user;
	
	public ShoppingCart() {}

	public ShoppingCart(Long id, Date salesDate, Set<ShoppingCartItem> items, User user) {		
		this.id = id;
		this.salesDate = salesDate;
		this.items = items;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public Set<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(Set<ShoppingCartItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", salesDate=" + salesDate + ", items=" + items
				+ ", user=" + user + "]";
	}
}
