package com.ftn.SBZ_2018.netgear.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.Preference;
import com.ftn.SBZ_2018.netgear.repository.PreferenceRepository;

@Service
public class PreferenceServiceImpl implements PreferenceService {

	@Autowired
	private PreferenceRepository preferenceRepo;
	
	@Override
	public Preference findById(Long id) {
		return preferenceRepo.findOneById(id);
	}

	@Override
	public List<Preference> getAllPreferences() {
		return preferenceRepo.findAll();
	}

	@Override
	public List<Preference> getAllUserPreferences(Long userId) {
		return preferenceRepo.findAll().stream()
				.filter(p -> p.getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}

	@Override
	public Preference insertPreference(Preference preference) {
		return preferenceRepo.save(preference);
	}

	@Override
	public Preference updatePreference(Preference preference) {
		return preferenceRepo.save(preference);
	}

	@Override
	public void deletePreference(Long id) {
		preferenceRepo.deleteById(id);
	}

}
