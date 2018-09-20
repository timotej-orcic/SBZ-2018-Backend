package com.ftn.SBZ_2018.netgear.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018.netgear.dao.NetworkSystemItemDAO;
import com.ftn.SBZ_2018.netgear.dao.PreferenceDAOFactory;
import com.ftn.SBZ_2018.netgear.dao.ProductPreferencesDAO;
import com.ftn.SBZ_2018.netgear.dao.ResponseWrapper;
import com.ftn.SBZ_2018.netgear.dao.SearchData;
import com.ftn.SBZ_2018.netgear.dao.SearchNetworkSystem;
import com.ftn.SBZ_2018.netgear.dao.SearchProduct;
import com.ftn.SBZ_2018.netgear.dao.ShoppingCartFactory;
import com.ftn.SBZ_2018.netgear.dao.ShoppingCartItemDAO;
import com.ftn.SBZ_2018.netgear.helpers.WebShopHelper;
import com.ftn.SBZ_2018.netgear.model.Preference;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.model.User;
import com.ftn.SBZ_2018.netgear.security.JwtUtils;
import com.ftn.SBZ_2018.netgear.service.PreferenceService;
import com.ftn.SBZ_2018.netgear.service.PreferenceTypeService;
import com.ftn.SBZ_2018.netgear.service.ProductService;
import com.ftn.SBZ_2018.netgear.service.ShoppingCartService;
import com.ftn.SBZ_2018.netgear.service.UserService;
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
	private PreferenceService preferenceService;
	
	@Autowired
	private PreferenceTypeService preferenceTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActiveUsersStore activeUsersStore;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Value("${utils.token.header}")
    private String tokenHeader;
	
	@PreAuthorize("hasAuthority('0')")
	@RequestMapping(value = "getProductParams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<List<SearchData>> getProductParams() {
		ResponseWrapper<List<SearchData>> retObj = new ResponseWrapper<List<SearchData>>();				    
	
		retObj.setPayload(WebShopHelper.getSearchData(productService));
		retObj.setMessage("Product params loaded successfully");
		retObj.setSuccess(true);
	    
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('0')")
	@RequestMapping(value = "findSingleProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ProductPreferencesDAO> findSingleProduct(@RequestBody SearchProduct product, ServletRequest request) {
		ResponseWrapper<ProductPreferencesDAO> retObj = new ResponseWrapper<ProductPreferencesDAO>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    if(username != null) {		    
		    List<Product> searchList = WebShopHelper.searchProducts(productService, product);
		    if(searchList.isEmpty()) {
		    	retObj.setPayload(null);
		    	retObj.setMessage("No results found");
		    	retObj.setSuccess(false);
		    }
		    else {
		    	User user = userService.findByUsername(username);
	    		ProductPreferencesDAO dao = new ProductPreferencesDAO();
		    	if(product.isIncludeUserPreferences()) {
	    			List<Preference> prefList = preferenceService.getAllUserPreferencesByProdType(user.getId(), product.getProductType());
	    			dao.setProducts(searchList);
	    			dao.setPreferences(PreferenceDAOFactory.createDaoList(prefList));
		    		retObj.setPayload(dao);
			    	retObj.setMessage("Advanced search successfull");
			    	retObj.setSuccess(true);		    		
		    	}
		    	else {
		    		dao.setProducts(searchList);
	    			dao.setPreferences(null);
		    		retObj.setPayload(dao);
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
	public ResponseWrapper<String> shop(@RequestBody List<ShoppingCartItemDAO> shoppingCart, ServletRequest request) {
		ResponseWrapper<String> retObj = new ResponseWrapper<String>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    if(username != null) {
		    User user = userService.findByUsername(username);
	    	ActiveUser activeUser = activeUsersStore.getActiveUsers().get(username);
		    KieSession userSession = activeUser.getKieSessions().get("userSession");
		    
		    ShoppingCart myCart = ShoppingCartFactory.createCart(productService, shoppingCart);
		    
		    userSession.getAgenda().getAgendaGroup("singleProduct").setFocus();
		    userSession.setGlobal("preferenceService", preferenceService);
		    userSession.setGlobal("preferenceTypeService", preferenceTypeService);
		    userSession.setGlobal("user", user);
		    
		    myCart.getItems().forEach(item -> {
		    	/*calculate preferences*/
		    	userSession.insert(item);
		    	userSession.fireAllRules();
		    	/*END calculate preferences*/
		    	Product p = productService.findById(item.getProduct().getId());
		    	p.setLagerQuantity(p.getLagerQuantity() - item.getQuantity());
		    	productService.updateProduct(p);
		    });
		    
		    for (FactHandle factHandle : userSession.getFactHandles()) {
		    	userSession.delete(factHandle);
	        }
		    
		    myCart.setSalesDate(new Date());
		    myCart.setUser(user);
		    shoppingCartService.insertShoppingCart(myCart);
		    
		    retObj.setPayload("");
	    	retObj.setMessage("Shopping suceeded");
	    	retObj.setSuccess(true);
	    }
	    else {
	    	retObj.setPayload(null);
	    	retObj.setMessage("Invalid username");
	    	retObj.setSuccess(false);
	    }
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('0')")
	@RequestMapping(value = "findNetworkSystem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<List<Product>> findNetworkSystem(@RequestBody SearchNetworkSystem networkSystemData, ServletRequest request) {
		ResponseWrapper<List<Product>> retObj = new ResponseWrapper<List<Product>>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    if(username != null) {
	    	User user = userService.findByUsername(username);
	    	ActiveUser activeUser = activeUsersStore.getActiveUsers().get(username);
		    KieSession userSession = activeUser.getKieSessions().get("userSession");
		    
		    userSession.getAgenda().getAgendaGroup("networkSystem").setFocus();
		    userSession.setGlobal("productService", productService);
		    userSession.setGlobal("preferenceService", preferenceService);
		    userSession.setGlobal("preferenceTypeService", preferenceTypeService);
		    userSession.setGlobal("user", user);
		    
		    userSession.insert(networkSystemData);
		    userSession.fireAllRules();
		    
		    QueryResults cables = userSession.getQueryResults("Get all Network system items by type", "LAN Cable");
		    List<NetworkSystemItemDAO> cheapestCables = WebShopHelper.getCheapestItems(cables);
	    }
	    else {
	    	retObj.setPayload(null);
	    	retObj.setMessage("Invalid username");
	    	retObj.setSuccess(false);
	    }
	    
		return retObj;
	}
}
