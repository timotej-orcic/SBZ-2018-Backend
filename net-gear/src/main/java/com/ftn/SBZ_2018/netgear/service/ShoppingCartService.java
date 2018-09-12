package com.ftn.SBZ_2018.netgear.service;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.ShoppingCart;

public interface ShoppingCartService {

	public ShoppingCart findById(Long id);
	public List<ShoppingCart> getAllShoppingCarts();
	public List<ShoppingCart> getAllUserCarts(Long userId);
	public ShoppingCart insertShoppingCart(ShoppingCart shoppingCart);
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	public void deleteShoppingCart(Long id);
}
