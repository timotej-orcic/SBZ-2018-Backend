package com.ftn.SBZ_2018.netgear.service;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.PreferenceType;

public interface PreferenceTypeService {

	public PreferenceType findById(Long id);
	public List<PreferenceType> getAllPreferenceTypes();
}
