package com.ftn.SBZ_2018.netgear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018.netgear.model.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Long>{

	public Preference findOneById(Long id);
}
