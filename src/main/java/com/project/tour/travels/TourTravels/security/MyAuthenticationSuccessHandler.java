package com.project.tour.travels.TourTravels.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.project.tour.travels.TourTravels.config.MyUser;
import com.project.tour.travels.TourTravels.config.SecurityUtils;
import com.project.tour.travels.TourTravels.config.WebAppProperties;
import com.project.tour.travels.TourTravels.domain.UserLoginActivity;
import com.project.tour.travels.TourTravels.exception.InvalidConfigurationException;
import com.project.tour.travels.TourTravels.repo.ConfigurationValueRepository;
import com.project.tour.travels.TourTravels.repo.UserLoginActivityRepository;
import com.project.tour.travels.TourTravels.repo.UserLoginProfileRepository;
import com.project.tour.travels.TourTravels.util.IntelUtil;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

@Component("myAuthenticationSuccessHandler")
class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private @Autowired(required = false) ILoginAuthenticationHandler loginSuccessHandlerHook;
	private @Autowired WebAppProperties props;
	private @Autowired UserLoginProfileRepository loginProfileRepository;
	private @Autowired ConfigurationValueRepository configurationValueRepository;
	private @Autowired UserLoginActivityRepository userLoginActivityRepository;

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		// calling the hook after login success- check impl in ui web projects
		if (loginSuccessHandlerHook != null) {
			handle(request, response, authentication);
		}
		MyUser principal = (MyUser) authentication.getPrincipal();
		request.getSession().setAttribute("ULP", principal.getId());
		startLoginActivity(authentication, request);
		clearAuthenticationAttributes(request);
	}

	private void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication, request);
		try {
			loginSuccessHandlerHook.onAuthenticationSuccessHook(request, response, authentication);
		} catch (InvalidConfigurationException cx) {
			redirectStrategy.sendRedirect(request, response, "/prelogin?loginNotAllowed=true");
		} catch (Exception mEx) {
			redirectStrategy.sendRedirect(request, response, "/error/middleware-down");
		}
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication, HttpServletRequest request) {
		String targetUrl = null;
		MyUser myUser = getMyUserDetails(authentication);
		String key = myUser.getId().toString();
		targetUrl = "/login/home";
		return targetUrl;
	}

	private MyUser getMyUserDetails(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return (MyUser) authentication.getPrincipal();
		}
		return null;
	}

	private void clearAuthenticationAttributes(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		// remove session attributes. these are used in admin or customer application.
		session.removeAttribute("USER_LOGIN_PROFILE_ID");
	}

	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private void startLoginActivity(Authentication authentication, HttpServletRequest request) {
		MyUser myUser = getMyUserDetails(authentication);
		String ipAddress = request.getHeader("X-Forwarded-For");
		String ip = IntelUtil.getClientIP(request);
		logger.info("Login activity Ip adress remoteAddtress:::::::new::: " + ipAddress);

		// create a login activity
		UserLoginActivity userLoginActivity = new UserLoginActivity();
		userLoginActivity.setUserLoginProfile(loginProfileRepository.findOne(myUser.getId()));
		userLoginActivity.setSessionId(request.getSession().getId());
		userLoginActivity.setChannel("WEB");
		userLoginActivity.setIpAddress(null != ipAddress ? ipAddress : ip);
		userLoginActivity.setUserAgentString(getUserAgentDetail(request));
		userLoginActivity.setSessionId(SecurityUtils.getCurrentUser().getSessionId());
		userLoginActivity.setTrackingId(SecurityUtils.getCurrentUser().getTrackingId());
		userLoginActivityRepository.saveAndFlush(userLoginActivity);
		// reset failed counter
		if (myUser != null) {
			logger.info("reset failed counter due to successful login");
			loginProfileRepository.resetIncorrectAttempts(myUser.getId());
		}
	}

	private String getUserAgentDetail(HttpServletRequest request) {
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
		return agent.getOperatingSystem().getName() + " / " + agent.getName() + "-"
				+ agent.getVersionNumber().getMajor() + "." + agent.getVersionNumber().getMinor();
	}
}