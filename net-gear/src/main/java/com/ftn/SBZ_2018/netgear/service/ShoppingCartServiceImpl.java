package com.ftn.SBZ_2018.netgear.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shopCartRepo;
	
	@Override
	public ShoppingCart findById(Long id) {
		return shopCartRepo.findOneById(id);
	}

	@Override
	public List<ShoppingCart> getAllShoppingCarts() {
		return shopCartRepo.findAll();
	}

	@Override
	public List<ShoppingCart> getAllUserCarts(Long userId) {
		return shopCartRepo.findAll().stream()
				.filter(p -> p.getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}

	@Override
	public ShoppingCart insertShoppingCart(ShoppingCart shoppingCart) {
		return shopCartRepo.save(shoppingCart);
	}

	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		return shopCartRepo.save(shoppingCart);
	}

	@Override
	public void deleteShoppingCart(Long id) {
		shopCartRepo.deleteById(id);
	}

}
