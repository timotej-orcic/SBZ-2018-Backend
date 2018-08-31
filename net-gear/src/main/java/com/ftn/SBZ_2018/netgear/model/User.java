package com.ftn.SBZ_2018.netgear.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String firstname;
	
	@Column(nullable=false, length=50)
	private String lastname;
	
	@Column(nullable=false, unique=true, length=80)
	private String email;

	@Column(nullable=false, unique=true, length=50)
	private String username;
	
	@Column(nullable=false, length=50)
	private String password;
	
	@ManyToOne(optional=false)
	private UserRole role;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<ShoppingCart> myShoppingCarts;
	
	public User() {}

	public User(Long id, String firstname, String lastname, String email, 
			String username, String password, UserRole role,
			Set<ShoppingCart> myShoppingCarts) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.myShoppingCarts = myShoppingCarts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Set<ShoppingCart> getMyShoppingCarts() {
		return myShoppingCarts;
	}

	public void setMyShoppingCarts(Set<ShoppingCart> myShoppingCarts) {
		this.myShoppingCarts = myShoppingCarts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", role=" + role + ", myShoppingCarts=" + myShoppingCarts + "]";
	}		
}
