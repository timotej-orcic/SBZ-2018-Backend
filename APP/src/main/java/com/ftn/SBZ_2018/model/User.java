package com.ftn.SBZ_2018.model;

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
	
	@Column(nullable=false, unique=true, length=50)
	private String email;
	
	@Column(nullable=false, length=50)
	private String password;
	
	@ManyToOne(optional=false)
	private UserRole role;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<ShoppingCart> myShoppingCarts;
}
