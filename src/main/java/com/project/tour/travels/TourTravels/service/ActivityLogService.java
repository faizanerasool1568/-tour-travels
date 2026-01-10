package com.project.tour.travels.TourTravels.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.project.tour.travels.TourTravels.config.SecurityUtils;
import com.project.tour.travels.TourTravels.domain.UserActivityLog;
import com.project.tour.travels.TourTravels.domain.UserLoginActivity;
import com.project.tour.travels.TourTravels.dto.UserActivityLogDTO;
import com.project.tour.travels.TourTravels.repo.UserActivityLogRepository;
import com.project.tour.travels.TourTravels.repo.UserLoginActivityRepository;
import com.project.tour.travels.TourTravels.repo.UserServiceRepository;

@Service
public class ActivityLogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityLogService.class);

	private @Autowired UserActivityLogRepository userActivityLogRepository;
	private @Autowired UserLoginActivityRepository userLoginActivityRepository;
	private @Autowired UserServiceRepository userAccessRepository;

	public void prepareAndInsert(String refNo, String action, String trackingId, String service) {
		LOGGER.info("-----------ActivityLogService : prepareAndInsert (BEGIN)-------------------------");
		LOGGER.info("ActivityLogService : prepareAndInsert : refNo :{} ", refNo);
		LOGGER.info("ActivityLogService : prepareAndInsert : privilege :{} ", action);
		LOGGER.info("ActivityLogService : prepareAndInsert : trackingId :{} ", trackingId);
		LOGGER.info("ActivityLogService : prepareAndInsert : service :{} ", service);
		UserActivityLogDTO dto = new UserActivityLogDTO();
		dto.setTrackingId(trackingId);
		dto.setService(service);
		dto.setUserAction(action);
		dto.setReferenceNumber(refNo);
		captureUserActivity(dto);
		LOGGER.info("-----------ActivityLogService : prepareAndInsert (END)-------------------------");
	}

	private void captureUserActivity(UserActivityLogDTO logDTO) {
		LOGGER.info("-----------ActivityLogService : captureUserActivity (BEGIN)-------------------------");
		UserLoginActivity userLoginActivity = userLoginActivityRepository.findOneByTrackingId(logDTO.getTrackingId());
		LOGGER.info("user's tracking id is : {}", logDTO.getTrackingId());
		LOGGER.info("ActivityLogService : captureUserActivity  userLoginActivity : {}", userLoginActivity);
		if (userLoginActivity != null) {
			UserActivityLog userLog = new UserActivityLog();
			userLog.setUserService(userAccessRepository.findByCode(logDTO.getService()).getId());
			userLog.setUserAction(logDTO.getUserAction());
			userLog.setActivityDateTime(LocalDateTime.now());
			userLog.setUserLoginActivity(userLoginActivity);
			if (StringUtils.isEmpty(logDTO.getReferenceNumber())) {
				userLog.setReferenceNumber("N/A");
			} else {
				userLog.setReferenceNumber(logDTO.getReferenceNumber());
			}

			if (!StringUtils.isEmpty(logDTO.getComments())) {
				userLog.setActivityDescription(logDTO.getComments());
			}

			userActivityLogRepository.save(userLog);
		} else {

		}
		LOGGER.info("-----------ActivityLogService : captureUserActivity (END)-------------------------");
	}

	public void endLoginActivity(String sessionId) {
		List<UserLoginActivity> userLoginActivities = userLoginActivityRepository
				.findAllBySessionIdAndEndDatetimeIsNull(sessionId);
		for (UserLoginActivity ua : userLoginActivities) {
			userLoginActivityRepository.updateloginEndTime(LocalDateTime.now(), ua.getId());
		}
	}

}