package com.project.tour.travels.TourTravels.repo;

import javax.persistence.Cacheable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.tour.travels.TourTravels.domain.ConfigurationValue;

@Repository
public interface ConfigurationValueRepository extends AbstractRepository<ConfigurationValue> {

	@Query(" from ConfigurationValue where code =:code ")
	ConfigurationValue getConfigurationValueByCode(@Param("code") String code);

	public ConfigurationValue findByConfigurationTypeCodeAndCode(String configurationTypeCode, String code);
	
	
}