package com.project.tour.travels.TourTravels.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.tour.travels.TourTravels.domain.UserLoginActivity;

@Repository
public interface UserLoginActivityRepository extends AbstractRepository<UserLoginActivity> {

	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("update UserLoginActivity u set u.endDatetime = ?1 where u.id = ?2")
	void updateloginEndTime(LocalDateTime endDateTime, Long id);

	@Query("from UserLoginActivity u where u.trackingId = ?1")
	UserLoginActivity findOneByTrackingId(String trackingId);

	List<UserLoginActivity> findAllBySessionIdAndEndDatetimeIsNull(String sessionId);

}