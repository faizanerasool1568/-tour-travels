package com.project.tour.travels.TourTravels.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.project.tour.travels.TourTravels.config.SecurityUtils;

/**
 * Created by kamal on 12/2/17.
 */
@Component
public class CorporateLoginAuthHandler implements ILoginAuthenticationHandler {

	private static final Logger logger = LoggerFactory.getLogger(CorporateLoginAuthHandler.class);

//	@Autowired
//	public CorporateLoginAuthHandler(CorporateUserSessionRefreshService refreshService,
//			PreLoginClientService preLoginClientService) {
//		this.refreshService = refreshService;
//		this.preLoginClientService = preLoginClientService;
//	}

	@Override
	public void onAuthenticationSuccessHook(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		logger.info("Corporate Customer UI Auth Login Success Handler called");
		Long ulpId = SecurityUtils.getCurrentUserLoginProfileId();
		// first time login based on the flag
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
//		logger.info("Corporate Customer UI Auth Failure Success Handler called");
//		// get from session attribute
//		if (httpServletRequest.getSession().getAttribute("LOGIN_ACIVITY_DTO") != null) {
//			UserLoginActivityDTO loginActivityDTO = (UserLoginActivityDTO) httpServletRequest.getSession()
//					.getAttribute("LOGIN_ACIVITY_DTO");
//			preLoginClientService.onLoginFailure(loginActivityDTO);
//		}
	}

	@Override
	public String getUserLoginSecurity(Long ulpId) {
		return null;
	}
}
