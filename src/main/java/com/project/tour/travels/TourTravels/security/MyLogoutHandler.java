package com.project.tour.travels.TourTravels.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.project.tour.travels.TourTravels.config.SecurityUtils;
import com.project.tour.travels.TourTravels.service.ActivityLogService;

@Component("myLogoutHandler")
class MyLogoutHandler implements LogoutHandler {

	private static final Logger logger = LoggerFactory.getLogger(MyLogoutHandler.class);
	private @Autowired ActivityLogService activityLogService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if (SecurityUtils.isAuthenticated()) {
			activityLogService.prepareAndInsert("-", "Logout", SecurityUtils.getCurrentUser().getTrackingId(),
					"ADMIN_LOGOUT");
			activityLogService.endLoginActivity(SecurityUtils.getCurrentUser().getSessionId());
		}
		logger.info("Inside Logout Handler Method..");
	}

}
