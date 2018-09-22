package com.ftn.SBZ_2018.netgear.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.ftn.SBZ_2018.netgear.dao.NetworkSystemItemDAO;
import com.ftn.SBZ_2018.netgear.dao.SearchData;
import com.ftn.SBZ_2018.netgear.dao.SearchProduct;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.model.ShoppingCartItem;
import com.ftn.SBZ_2018.netgear.service.PreferenceService;
import com.ftn.SBZ_2018.netgear.service.ProductService;
import com.ftn.SBZ_2018.netgear.service.ShoppingCartService;

public class WebShopHelper {
	
	public static List<SearchData> getSearchData(ProductService productService) {
		List<SearchData> result = new ArrayList<SearchData>();
		
		List<String> productTypes = new ArrayList<String>();
		productService.getAllProducts().forEach(product -> {
			if(!productTypes.contains(product.getType())) {
				productTypes.add(product.getType());
			}
		});
		productTypes.forEach(pt -> {
			List<String> manufactorers = new ArrayList<String>();
			productService.getAllProductsByType(pt).forEach(product -> {
				if(!manufactorers.contains(product.getManufactorer())) {
					manufactorers.add(product.getManufactorer());
				}
			});
			SearchData searchData = new SearchData();
			searchData.setProductType(pt);
			searchData.setManufactorers(manufactorers);
			result.add(searchData);
		});
		
		return result;
	}
	
	public static List<Product> searchProducts(ProductService productService, SearchProduct searchProduct) {
		
		List<Product> result = productService.getAllProducts().stream()
				.filter(p -> p.getType().equals(searchProduct.getProductType()))
				.collect(Collectors.toList());
		
		if(searchProduct.getManufactorer() != "") {
			result = result.stream()
					.filter(p -> p.getManufactorer().equals(searchProduct.getManufactorer()))
					.collect(Collectors.toList());
		}
		
		if(searchProduct.getPriceMin() != null) {
			result = result.stream()
					.filter(p -> p.getPrice() >= searchProduct.getPriceMin())
					.collect(Collectors.toList());
		}
		
		if(searchProduct.getPriceMax() != null) {
			result = result.stream()
					.filter(p -> p.getPrice() <= searchProduct.getPriceMax())
					.collect(Collectors.toList());
		}
		
		result = result.stream()
				.filter(p -> p.getWarrantyInMonths() >= searchProduct.getWarrantyInMonthsMin())
				.collect(Collectors.toList());
		
		return result;
	}
	
	public static List<ShoppingCartItem> getAllUserShoppedItemsByType(ShoppingCartService shoppingCartService, Long userId, String productType) {
		List<ShoppingCart> myCarts = shoppingCartService.getAllUserCarts(userId);
		List<ShoppingCartItem> result = new ArrayList<ShoppingCartItem>();
		
		myCarts.forEach(cart -> {
			Set<ShoppingCartItem> items = cart.getItems();
			items.forEach(item -> {
				if(item.getProduct().getType().equals(productType)) {
					result.add(item);
				}
			});
		});
		
		return result;
	}
	
	public static List<NetworkSystemItemDAO> getCheapestItems(QueryResults queryList) {
		List<NetworkSystemItemDAO> result = new ArrayList<NetworkSystemItemDAO>();
		
		List<NetworkSystemItemDAO> items = new ArrayList<NetworkSystemItemDAO>();
		for (QueryResultsRow row : queryList) {
			NetworkSystemItemDAO item = (NetworkSystemItemDAO) row.get("$nsi");
			if(item.getQuantity() == 0)
				return null;
			items.add(item);
		}
		
		//sort list by prices
		Collections.sort(items, new Comparator<NetworkSystemItemDAO>(){
		     public int compare(NetworkSystemItemDAO o1, NetworkSystemItemDAO o2){
		         if(o1.getProduct().getPrice() == o2.getProduct().getPrice())
		             return 0;
		         return o1.getProduct().getPrice() < o2.getProduct().getPrice() ? -1 : 1;
		     }
		});
		
		//get cheapest item(s)
		NetworkSystemItemDAO firstItem = items.get(0);
		Double firstPrice = firstItem.getProduct().getPrice();
		result.add(firstItem);
		for (int i = 1; i < items.size(); i++) {
			NetworkSystemItemDAO nextItem = items.get(i);
			Double nextPrice = nextItem.getProduct().getPrice();
			if(firstPrice == nextPrice) {
				result.add(nextItem);
			}
			else {
				break;
			}
		}
		
		return result;
	}
	
	public static NetworkSystemItemDAO getBestByPreference(PreferenceService preferenceService,
			List<NetworkSystemItemDAO> items, Long userId) {
		Double maxSum = 0.0;
		int maxIndex = 0;
		for (int i = 0; i < items.size(); i++) {
			Double prefSum = preferenceService.getAllUserPreferencesByProdType(userId, items.get(i).getProduct().getType())
					.stream().mapToDouble(p -> p.getPercentage()).sum();
			if(prefSum > maxSum) {
				maxSum = prefSum;
				maxIndex = i;
			}
		}
		return items.get(maxIndex);
	}
	
	public static Double calculatePrice(List<NetworkSystemItemDAO> items) {		
		return items.stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum(); 
	}
}
