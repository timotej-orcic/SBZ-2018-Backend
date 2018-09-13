package com.ftn.SBZ_2018.netgear.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018.helpers.WebShopHelper;
import com.ftn.SBZ_2018.netgear.dao.ResponseWrapper;
import com.ftn.SBZ_2018.netgear.dao.SearchProduct;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.security.JwtUtils;
import com.ftn.SBZ_2018.netgear.service.ProductService;
import com.ftn.SBZ_2018.netgear.service.ShoppingCartService;
import com.ftn.SBZ_2018.netgear.userDetails.ActiveUser;
import com.ftn.SBZ_2018.netgear.userDetails.ActiveUsersStore;

@RestController
@RequestMapping(value = "rest/secured/web-shop/")
public class WebShopController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ActiveUsersStore activeUsersStore;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Value("${utils.token.header}")
    private String tokenHeader;
	
	@PreAuthorize("hasAuthority('0')")
	@RequestMapping(value = "setAgenda", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<List<List<String>>> setAgenda(@RequestParam(value = "agenda", required = true) String agenda, ServletRequest request) {
		ResponseWrapper<List<List<String>>> retObj = new ResponseWrapper<List<List<String>>>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    if(username != null) {
		    ActiveUser activeUser = activeUsersStore.getActiveUsers().get(username);
		    KieSession userSession = activeUser.getKieSessions().get("userSession");
		    
	    	List<List<String>> payload = new ArrayList<List<String>>();
	    	payload.add(WebShopHelper.getProductTypes(productService));
	    	payload.add(WebShopHelper.getManufactorers(productService));
		    
		    if(agenda.equals("singleProduct")) {
		    	userSession.getAgenda().getAgendaGroup("singleProduct").setFocus();
		    	retObj.setPayload(payload);
		    	retObj.setMessage("Single products agenda is set");
		    	retObj.setSuccess(true);
		    }
		    else {
		    	userSession.getAgenda().getAgendaGroup("networkSystem").setFocus();
		    	retObj.setPayload(payload);
		    	retObj.setMessage("Network systems agenda is set");
		    	retObj.setSuccess(true);
		    }	
	    }   
	    else {
	    	retObj.setPayload(null);
	    	retObj.setMessage("Invalid username");
	    	retObj.setSuccess(false);
	    }
	    
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('0')")
	@RequestMapping(value = "findSingleProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<List<Product>> findSingleProduct(@RequestBody SearchProduct product, ServletRequest request) {
		ResponseWrapper<List<Product>> retObj = new ResponseWrapper<List<Product>>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    if(username != null) {
	    	ActiveUser activeUser = activeUsersStore.getActiveUsers().get(username);
		    KieSession userSession = activeUser.getKieSessions().get("userSession");
		    
		    List<Product> searchList = WebShopHelper.searchProducts(productService, product);
		    if(searchList.isEmpty()) {
		    	
		    }
		    else {
		    	if(product.isIncludeUserPreferences()) {
		    		userSession.insert(searchList);
		    		userSession.fireAllRules();
		    		retObj.setPayload(searchList);
			    	retObj.setMessage("Advanced search successfull");
			    	retObj.setSuccess(true);
		    		
		    	}
		    	else {
		    		retObj.setPayload(searchList);
			    	retObj.setMessage("Basic search successfull");
			    	retObj.setSuccess(true);
		    	}
		    }		    		   
	    }
	    else {
	    	retObj.setPayload(null);
	    	retObj.setMessage("Invalid username");
	    	retObj.setSuccess(false);
	    }
	    
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('0')")
	@RequestMapping(value = "shop", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> shop(@RequestBody ShoppingCart shoppingCart, ServletRequest request) {
		ResponseWrapper<String> retObj = new ResponseWrapper<String>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    if(username != null) {
	    	ActiveUser activeUser = activeUsersStore.getActiveUsers().get(username);
		    KieSession userSession = activeUser.getKieSessions().get("userSession");
	    }
	    else {
	    	
	    }
		
		return retObj;
	}
}
