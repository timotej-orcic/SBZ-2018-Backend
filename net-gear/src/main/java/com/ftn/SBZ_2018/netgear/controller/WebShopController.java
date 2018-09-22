package com.ftn.SBZ_2018.netgear.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.kie.api.KieBase;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.ClassObjectFilter;
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
		    
		    userSession.setGlobal("preferenceService", preferenceService);
		    userSession.setGlobal("preferenceTypeService", preferenceTypeService);
		    userSession.setGlobal("user", user);
		    
		    myCart.getItems().forEach(item -> {
		    	/*calculate preferences*/
			    userSession.getAgenda().getAgendaGroup("singleProduct").setFocus();
		    	userSession.insert(item);
		    	userSession.fireAllRules();
			    for (FactHandle factHandle : userSession.getFactHandles()) {
			    	userSession.delete(factHandle);
		        }
		    	/*END calculate preferences*/
		    	Product p = productService.findById(item.getProduct().getId());
		    	p.setLagerQuantity(p.getLagerQuantity() - item.getQuantity());
		    	productService.updateProduct(p);
		    });
		    
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
	public ResponseWrapper<List<NetworkSystemItemDAO>> findNetworkSystem(@RequestBody SearchNetworkSystem networkSystemData, ServletRequest request) {
		ResponseWrapper<List<NetworkSystemItemDAO>> retObj = new ResponseWrapper<List<NetworkSystemItemDAO>>();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = jwtUtils.getUsernameFromToken(token);
		
	    List<NetworkSystemItemDAO> payload = new ArrayList<NetworkSystemItemDAO>();
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
		    
		    int ethernetExtCnt = 0;
		    int routersCnt = 0;
		    int switchesCnt = 0;
		    KieBase kbase = userSession.getKieBase();
		    FactType nshType = kbase.getFactType( "userRules","NetworkSystemHelper" );
		    Collection<?> nshObjects = userSession.getObjects(new ClassObjectFilter(nshType.getFactClass()));
		    for (Object o : nshObjects) {
		    	Class<?> nshClass = o.getClass();
		    	for (Field field : nshClass.getDeclaredFields()) {
		    		if(field.getName().equals("ethernetExtendersCount")) {
		    			field.setAccessible(true);
						try {
			    			ethernetExtCnt = (int) field.getInt(o);
						} 
						 catch (IllegalAccessException e) {
							e.printStackTrace();
						}
		    		}
		    		if(field.getName().equals("routersCount")) {
		    			field.setAccessible(true);
						try {
							routersCnt = (int) field.getInt(o);
						} 
						 catch (IllegalAccessException e) {
							e.printStackTrace();
						}
		    		}
		    		if(field.getName().equals("switchesCount")) {
		    			field.setAccessible(true);
						try {
							switchesCnt = (int) field.getInt(o);
						} 
						 catch (IllegalAccessException e) {
							e.printStackTrace();
						}
		    		}
		    	}
		    }
		    if(networkSystemData.getStreetCabling().equals("Copper")) {
		    	//cables
			    QueryResults cables = userSession.getQueryResults("Get all Network system items by type", "LAN Cable");
			    if(cables.size() == 0) {
			    	retObj.setPayload(null);
			    	retObj.setMessage("Could not find the specified elements [LAN Cable], please try again");
			    	retObj.setSuccess(false);
			    	return retObj;
			    }
			    else {
				    List<NetworkSystemItemDAO> cheapestCables = WebShopHelper.getCheapestItems(cables);
			    	if(cheapestCables.size() == 1) {
			    		payload.add(cheapestCables.get(0));
			    	}
			    	else {
			    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestCables, user.getId()));
			    	}
			    }
			    //ethernet extenders are not needed
		    }
		    else {
		    	//cables
		    	QueryResults cables = userSession.getQueryResults("Get all Network system items by type", "Optical Cable");
			    if(cables.size() == 0) {
			    	retObj.setPayload(null);
			    	retObj.setMessage("Could not find the specified elements [Optical Cable], please try again");
			    	retObj.setSuccess(false);
			    	return retObj;
			    }
			    else {
				    List<NetworkSystemItemDAO> cheapestCables = WebShopHelper.getCheapestItems(cables);
			    	if(cheapestCables.size() == 1) {
			    		payload.add(cheapestCables.get(0));
			    	}
			    	else {
			    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestCables, user.getId()));
			    	}
			    }
		    	//ethernet extenders (can be 0)
			    if(ethernetExtCnt > 0) {
			    	QueryResults ethernetExtenders = userSession.getQueryResults("Get all Network system items by type", "Ethernet extender");
			    	if (ethernetExtenders.size() == 0) {
				    	retObj.setPayload(null);
				    	retObj.setMessage("Could not find the specified elements [Ethernet extender], please try again");
				    	retObj.setSuccess(false);
				    	return retObj;
				    }
				    else {
					    List<NetworkSystemItemDAO> cheapestExtenders = WebShopHelper.getCheapestItems(ethernetExtenders);
					    if(cheapestExtenders != null) {
					    	if(cheapestExtenders.size() == 1) {
					    		payload.add(cheapestExtenders.get(0));
					    	}
					    	else {
					    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestExtenders, user.getId()));
					    	}
					    }
				    }
			    }			   
		    }
		    //routers (can be 0)
		    if(routersCnt > 0) {
			    QueryResults routers = userSession.getQueryResults("Get all Network system items by type", "Router");
			    if (routers.size() == 0) {
			    	retObj.setPayload(null);
			    	retObj.setMessage("Could not find the specified elements [Router], please try again");
			    	retObj.setSuccess(false);
			    	return retObj;
			    }
			    else {
				    List<NetworkSystemItemDAO> cheapestRouters = WebShopHelper.getCheapestItems(routers);
				    if(cheapestRouters != null) {
				    	if(cheapestRouters.size() == 1) {
				    		payload.add(cheapestRouters.get(0));
				    	}
				    	else {
				    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestRouters, user.getId()));
				    	}
				    }
			    }
		    }
		    //switches (can be 0)
		    if(switchesCnt > 0) {
			    QueryResults switches = userSession.getQueryResults("Get all Network system items by type", "Switch");
			    if (switches.size() == 0) {
			    	retObj.setPayload(null);
			    	retObj.setMessage("Could not find the specified elements [Switch], please try again");
			    	retObj.setSuccess(false);
			    	return retObj;
			    }
			    else {
				    List<NetworkSystemItemDAO> cheapestSwitches = WebShopHelper.getCheapestItems(switches);
				    if(cheapestSwitches != null) {
				    	if(cheapestSwitches.size() == 1) {
				    		payload.add(cheapestSwitches.get(0));
				    	}
				    	else {
				    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestSwitches, user.getId()));
				    	}
				    }
			    }
		    }
		    //cable trays
		    QueryResults cableTrays = userSession.getQueryResults("Get all Network system items by type", "Cable tray");
		    if (cableTrays.size() == 0) {
		    	retObj.setPayload(null);
		    	retObj.setMessage("Could not find the specified elements [Cable tray], please try again");
		    	retObj.setSuccess(false);
		    	return retObj;
		    }
		    else {
			    List<NetworkSystemItemDAO> cheapestTrays = WebShopHelper.getCheapestItems(cableTrays);
		    	if(cheapestTrays.size() == 1) {
		    		payload.add(cheapestTrays.get(0));
		    	}
		    	else {
		    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestTrays, user.getId()));
		    	}	
		    }
		    //telecommunication closets
		    QueryResults teleClosets = userSession.getQueryResults("Get all Network system items by type", "Telecommunication closet");
		    if (teleClosets.size() == 0) {
		    	retObj.setPayload(null);
		    	retObj.setMessage("Could not find the specified elements [Telecommunication closet], please try again");
		    	retObj.setSuccess(false);
		    	return retObj;
		    }
		    else {
			    List<NetworkSystemItemDAO> cheapestTeleClosets = WebShopHelper.getCheapestItems(teleClosets);
		    	if(cheapestTeleClosets.size() == 1) {
		    		payload.add(cheapestTeleClosets.get(0));
		    	}
		    	else {
		    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestTeleClosets, user.getId()));
		    	}	
		    }
		    //patch panels
		    QueryResults patchPanels = userSession.getQueryResults("Get all Network system items by type", "Patch panel");
		    if(patchPanels.size() == 0) {
		    	retObj.setPayload(null);
		    	retObj.setMessage("Could not find the specified elements [Patch panel], please try again");
		    	retObj.setSuccess(false);
		    	return retObj;
		    }
		    else {
			    List<NetworkSystemItemDAO> cheapestPatchPanels = WebShopHelper.getCheapestItems(patchPanels);
		    	if(cheapestPatchPanels.size() == 1) {
		    		payload.add(cheapestPatchPanels.get(0));
		    	}
		    	else {
		    		payload.add(WebShopHelper.getBestByPreference(preferenceService, cheapestPatchPanels, user.getId()));
		    	}	
		    }
		    
		    for (FactHandle factHandle : userSession.getFactHandles()) {
		    	userSession.delete(factHandle);
	        }
	    }
	    else {
	    	retObj.setPayload(null);
	    	retObj.setMessage("Invalid username");
	    	retObj.setSuccess(false);
	    }
	    
	    Double priceTotal = WebShopHelper.calculatePrice(payload);
	    if(priceTotal <= networkSystemData.getBudget()) {
		    retObj.setPayload(payload);
		    retObj.setMessage("Successfull search");
	    	retObj.setSuccess(true);
	    }	   
	    else {
	    	retObj.setPayload(null);
	    	retObj.setMessage("Budget was not met [total price = " + priceTotal +"]");
	    	retObj.setSuccess(false);
	    }
	    
	    
		return retObj;
	}
}
