package com.ftn.SBZ_2018.netgear.service;

import java.util.List;
import java.util.Optional;

import com.ftn.SBZ_2018.netgear.model.PreferenceType;

public interface PreferenceTypeService {

	public PreferenceType findById(Long id);
	public Optional<PreferenceType> findByName(String name);
	public List<PreferenceType> getAllPreferenceTypes();	
}
