package com.ftn.SBZ_2018.netgear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.PreferenceType;
import com.ftn.SBZ_2018.netgear.repository.PreferenceTypeRepository;

@Service
public class PreferenceTypeServiceImpl implements PreferenceTypeService {

	@Autowired
	private PreferenceTypeRepository prefTypeRepo;
	
	@Override
	public PreferenceType findById(Long id) {
		return prefTypeRepo.findOneById(id);
	}

	@Override
	public Optional<PreferenceType> findByName(String name) {
		return prefTypeRepo.findAll().stream()
				.filter(p -> p.getName().equals(name)).findFirst();
	}
	
	@Override
	public List<PreferenceType> getAllPreferenceTypes() {
		return prefTypeRepo.findAll();
	}
}
