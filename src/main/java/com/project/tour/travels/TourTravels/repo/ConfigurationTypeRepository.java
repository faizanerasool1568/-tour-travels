package com.project.tour.travels.TourTravels.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.tour.travels.TourTravels.domain.ConfigurationType;

@Repository
public interface ConfigurationTypeRepository extends AbstractRepository<ConfigurationType> {

	@Query(" from ConfigurationType where code =:code ")
	ConfigurationType getConfigurationTypeByCode(@Param("code") String code);

}