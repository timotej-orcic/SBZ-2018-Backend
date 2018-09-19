package com.ftn.SBZ_2018.netgear.dao;

import java.util.ArrayList;
import java.util.List;

import com.ftn.SBZ_2018.netgear.model.Preference;

public class PreferenceDAOFactory {

	public static List<PreferenceDAO> createDaoList(List<Preference> preferences) {
		List<PreferenceDAO> result = new ArrayList<PreferenceDAO>();
		
		preferences.forEach(p -> {
			PreferenceDAO dao = new PreferenceDAO();
			dao.setId(p.getId());
			dao.setUserId(p.getUser().getId());
			dao.setProductType(p.getProductType());
			dao.setPreferenceType(p.getPreferenceType().getName());
			dao.setValue(p.getValue());
			dao.setProductsCount(p.getProductsCount());
			dao.setPercentage(p.getPercentage());
			result.add(dao);
		});
		
		return result;
	}
}
