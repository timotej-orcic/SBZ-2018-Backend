package com.ftn.SBZ_2018.netgear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018.netgear.model.PreferenceType;

public interface PreferenceTypeRepository extends JpaRepository<PreferenceType, Long>{

	public PreferenceType findOneById(Long id);
}
