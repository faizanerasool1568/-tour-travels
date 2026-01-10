package com.project.tour.travels.TourTravels.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.tour.travels.TourTravels.domain.UserLoginProfile;

@Repository
public interface UserLoginProfileRepository extends AbstractRepository<UserLoginProfile> {

	@Query(" from UserLoginProfile where userId =:userId ")
	UserLoginProfile getUserLoginProfileByUserId(@Param("userId") String userId);
	
	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("update UserLoginProfile u set u.failureCounter = 0,u.lockedDateTime=null where u.id = ?1 ")
	void resetIncorrectAttempts(Long id);

}