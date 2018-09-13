package com.ftn.SBZ_2018.netgear.service;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.Preference;

public interface PreferenceService {

	public Preference findById(Long id);
	public List<Preference> getAllPreferences();
	public List<Preference> getAllUserPreferences(Long userId);
	public Preference insertPreference(Preference preference);
	public Preference updatePreference(Preference preference);
	public void deletePreference(Long id);
}
