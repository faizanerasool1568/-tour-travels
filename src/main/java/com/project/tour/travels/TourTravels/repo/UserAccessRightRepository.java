package com.project.tour.travels.TourTravels.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.tour.travels.TourTravels.domain.UserAccessRight;

@Repository
public interface UserAccessRightRepository extends AbstractRepository<UserAccessRight> {

	@Query(" from UserAccessRight where userLoginProfileId.id =:id and status.id=:statusId")
	List<UserAccessRight> getUserAccessRightByUserLoginProfileId(@Param("id") Long id,
			@Param("statusId") Long statusId);
	
	@Query(" from UserAccessRight where userLoginProfileId.id =:id and userService.id=:serviceId")
	UserAccessRight getUserAccessRightByUserLoginProfileIdAndUserService(@Param("id") Long id,
			@Param("serviceId") Long serviceId);

}