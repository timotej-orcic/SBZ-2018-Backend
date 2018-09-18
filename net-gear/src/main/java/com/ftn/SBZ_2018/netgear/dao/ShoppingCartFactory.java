package com.ftn.SBZ_2018.netgear.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.model.ShoppingCartItem;
import com.ftn.SBZ_2018.netgear.service.ProductService;

public class ShoppingCartFactory {

	public static ShoppingCart createCart(ProductService productService, List<ShoppingCartItemDAO> daoList) {
		ShoppingCart retObj = new ShoppingCart();
		
		Set<ShoppingCartItem> itemsList = new HashSet<ShoppingCartItem>();
		daoList.forEach(dao -> {
			Product p = productService.findById(dao.getProductId());
			ShoppingCartItem item = new ShoppingCartItem();
			item.setQuantity(dao.getQuantity());
			item.setProduct(p);
			item.setPrice(dao.getPrice());
			item.setShoppingCart(retObj);
			itemsList.add(item);
		});
		retObj.setItems(itemsList);
		
		return retObj;
	}
}
