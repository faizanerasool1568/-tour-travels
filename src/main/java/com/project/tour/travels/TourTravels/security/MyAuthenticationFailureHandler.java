//package com.project.tour.travels.TourTravels.security;
//
//import java.io.IOException;
//import java.util.Locale;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.session.SessionAuthenticationException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.ResourceAccessException;
//import org.springframework.web.servlet.LocaleResolver;
//
//import com.project.tour.travels.TourTravels.config.WebAppProperties;
//import com.vayana.boot.common.dto.CommonPushNotificationDto;
//import com.vayana.boot.common.dto.EfmDetailDTO;
//import com.vayana.boot.common.dto.PreLoginDTO;
//import com.vayana.boot.common.dto.UserLoginActivityDTO;
//import com.vayana.boot.common.dto.user.UserPasswordPolicyResponseDTO;
//import com.vayana.boot.common.error.SecurityBreachException;
//import com.vayana.boot.common.service.IUserActivityService;
//import com.vayana.boot.common.util.DefaultProfileUtil;
//import com.vayana.boot.common.util.InetUtil;
//import com.vayana.boot.infra.web.client.service.EfmDetailClientService;
//import com.vayana.boot.infra.web.security.captcha.CaptchaUtils;
//import com.vayana.boot.infra.web.security.exception.FirstTimeUserException;
//import com.vayana.boot.infra.web.security.exception.ForceChangePasswordException;
//import com.vayana.boot.infra.web.security.handler.hook.IUserPasswordPolicyHandler;
//import com.vayana.boot.infra.web.util.SecurityBreachUtil;
//
//import net.sf.uadetector.ReadableUserAgent;
//import net.sf.uadetector.UserAgentStringParser;
//import net.sf.uadetector.service.UADetectorServiceFactory;
//
//@Component("authenticationFailureHandler")
//class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
//	private final Logger log = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
//
//	private final Long FIRST_USER_LOCKED = 72152L;
//	private final Long USER_LOCKED = 72130L;
//	private final Long INACTIVE_USER = 72180L;
//
//	private final Long DEACTIVATE_USER = 72153L;
//	private static final String PRE_LOGIN = "preLogin";
//
//	private @Autowired Environment env;
//	private @Autowired WebAppProperties props;
//	private @Autowired IUserActivityService userActivityService;
//	private @Autowired EfmDetailClientService efmClientService;
//
//	@Autowired
//	private MessageSource messages;
//
//	// CorporateLoginAuthHandler is the implementation class for
//	// ILoginAuthenticationHandler interface
//	private @Autowired(required = false) ILoginAuthenticationHandler loginAuthenticationHandler;
//	private @Autowired(required = false) IUserPasswordPolicyHandler passwordPolicyHandler;
//
//	@Autowired
//	private LocaleResolver localeResolver;
//
//	private String determineTargetUrl(String targetUrl, String lang) {
//		if (lang == null) {
//			return targetUrl;
//		}
//		return props.isUserLocaleEnabled() ? targetUrl + "?lang=" + lang : targetUrl;
//	}
//
//	@Override
//	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
//			final AuthenticationException exception) throws IOException, ServletException {
//
//		if (exception instanceof FirstTimeUserException) {
//			CaptchaUtils.destroyCaptcha(request.getSession());
//
//			FirstTimeUserException firstEx = (FirstTimeUserException) exception;
//			// STORE THE ULP ID IN SESSION
//			request.getSession().setAttribute("USER_LOGIN_PROFILE_ID", firstEx.getUlpId());
//			SecurityBreachUtil.startSecurityPending(request.getSession(), SecurityBreachException.CUST_FIRSTTIME_LOGIN);
//
//			if (DefaultProfileUtil.isProfileExits(env, "admin")) {
//				setDefaultFailureUrl(determineTargetUrl(props.getFirstTimeLoginUrl(), firstEx.getLang()));
//			} else {
//				if (props.getSecurity().getOtpEnabled()) {
//					log.info("OTP is enabled");
//					SecurityBreachUtil.start2FA(request.getSession());
//					setDefaultFailureUrl(determineTargetUrl("/login/two-factor-auth/generate", firstEx.getLang()));
//				} else {
//					log.info("OTP is disabled");
//					setDefaultFailureUrl(determineTargetUrl(props.getFirstTimeLoginUrl(), firstEx.getLang()));
//				}
//			}
//			super.onAuthenticationFailure(request, response, exception);
//			return;
//		} else if (exception instanceof ForceChangePasswordException) {
//			CaptchaUtils.destroyCaptcha(request.getSession());
//			ForceChangePasswordException forcePwdEx = (ForceChangePasswordException) exception;
//			// STORE THE ULP ID IN SESSION
//			request.getSession().setAttribute("USER_LOGIN_PROFILE_ID", forcePwdEx.getUlpId());
//			SecurityBreachUtil.startSecurityPending(request.getSession(),
//					SecurityBreachException.FORCE_CHANGE_PASSWORD);
//			setDefaultFailureUrl(determineTargetUrl("/change-password/login", forcePwdEx.getLang()));
//			super.onAuthenticationFailure(request, response, exception);
//			return;
//		}
//		String username = request.getParameter("username");
//		Long ulpId;
//		final Locale locale = localeResolver.resolveLocale(request);
//		log.info("calling update Last Failure Login date for Username {}", username);
//
//			boolean isUsernameNotFoundException = exception instanceof UsernameNotFoundException;
//			boolean isRemainingAttemptZero = userPasswordPolicy != null && userPasswordPolicy.getRemainingAttempt()!=null? userPasswordPolicy.getRemainingAttempt().equalsIgnoreCase("0"):false;
//
//			if ((groupId.equals(1) && isUsernameNotFoundException) || isRemainingAttemptZero) {
//			    setDefaultFailureUrl(determineTargetUrl("/login/blocked", locale.getLanguage()));
//			    super.onAuthenticationFailure(request, response, exception);
//			    CommonPushNotificationDto commonDto = new CommonPushNotificationDto();
//				commonDto.setUserId(userPasswordPolicy.getUlpId());
//				 commonDto.setToken("password");
//				 commonDto.setTokenAr("كلمة المرور");
//			    efmClientService.sendUserLockNotification(commonDto);
//			    return;
//			}
//			// Rest of the code...
//
//		}
//
//	// set the enableCaptcha attribute to true in session
//
//	if(userPasswordPolicy!=null&&userPasswordPolicy.isEnableCaptcha())
//
//	{
//		// Get the session
//		HttpSession session = request.getSession();
//		session.setAttribute("enableCaptcha", true);
//		session.setAttribute("pwdCaptcha", true);
//	}
//
//	// read the exception and then get the error message from i18n
//	String errorMessage = prepareErrorMessage(exception, groupId, username, request, userPasswordPolicy, locale);
//
//	super.onAuthenticationFailure(request,response,exception);
//
//	log.warn("Error from Security : {}",errorMessage);request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,errorMessage);
//	// SecurityUtils.printSessionAttributes(request.getSession());
//
//	// call the auth failure hook
//	loginAuthenticationHandler.onAuthenticationFailure(request,response,exception);
//	}
//
//	private String prepareErrorMessage(AuthenticationException exception, Long groupId, String username,
//			HttpServletRequest request, UserPasswordPolicyResponseDTO passwordPolicyResponseDTO, Locale locale) {
//		String errorMessage = null;
//		try {
//			if (exception instanceof BadCredentialsException) {
//				BadCredentialsException bex = (BadCredentialsException) exception;
//
//				if ("message.userEffectiveFromDateError".equals(exception.getMessage())
//						|| "message.service.unavailable".equals(exception.getMessage())
//						|| "message.userEffectiveToDateError".equals(exception.getMessage())
//						|| "message.kycexpired".equals(exception.getMessage())
//						|| "message.expiredDateAttempt".equals(exception.getMessage())
//						|| "message.expiredDobAttempt".equals(exception.getMessage())) {
//
//					errorMessage = messages.getMessage(exception.getMessage(), bex.getParams(), locale);
//					return errorMessage;
//				}
//				
//				if ("message.badCaptcha".equals(exception.getMessage())) {
//					setDefaultFailureUrl(props.getLoginURL() + "?captchaError=true");
//					errorMessage = messages.getMessage(exception.getMessage(), bex.getParams(), locale);
//					return errorMessage;
//				}
//				if (exception.getMessage().contains("IM:")) {
//					request.getSession().removeAttribute("enableCaptcha");
//					return exception.getMessage().replace("IM:", "").replace("\"", "");
//				}
//				if (exception.getMessage().contains("cib.")) {
//					return messages.getMessage(exception.getMessage().replaceAll("^\"|\"$", ""), null, locale);
//				}
//				if (exception.getMessage().contains("SES:")) {
//					log.info("Error on Logon prepareErrorMessage " + exception.getMessage());
//					return messages.getMessage("message.maxSessionsExceeded", null, locale);
//				}
//
//				errorMessage = prepareUserPasswordPolicyErrorMessages(exception, groupId, username, request, locale,passwordPolicyResponseDTO);
//				
//				/*
//				 * if (groupId.equals(1) && passwordPolicyResponseDTO != null &&
//				 * passwordPolicyResponseDTO.getRemainingAttempt().equalsIgnoreCase("0")) {
//				 * errorMessage = "Invalid input"; // Set the desired error message for invalid
//				 * input }
//				 */
//
//			} else if (exception instanceof SessionAuthenticationException) {
//				errorMessage = messages.getMessage("message.maxSessionsExceeded", null, locale);
//			}
//		} catch (ResourceAccessException ex) {
//			logger.info(ex.getMessage(), ex);
//			errorMessage = "<center>" + messages.getMessage("message.apiserver.not.running", null, locale)
//					+ "</center>";
//		}
//
//		return errorMessage;
//	}
//
//	private String prepareUserPasswordPolicyErrorMessages(AuthenticationException exception, Long groupId,
//	        String username, HttpServletRequest request, Locale locale,
//	        UserPasswordPolicyResponseDTO userPasswordPolicy) {
//	    String errorMessage = null;
//	    String remainingAttemptValue = null;
//	    Long ulpId = null;
//
//	    if (userPasswordPolicy == null) {
//	        errorMessage = messages.getMessage("message.badCredentials", null, locale);
//	    } else if (userPasswordPolicy.getUserStatus().equals(INACTIVE_USER)) {
//	        errorMessage = messages.getMessage("message.userDisabled", null, locale);
//	    } else if (userPasswordPolicy.getLoginStatus().equals(DEACTIVATE_USER)) {
//	        return messages.getMessage("message.user.deactivated", null, locale);
//	    } else {
//	        errorMessage = messages.getMessage(exception.getMessage(), null, locale);
//	        
//	        if(groupId==1 && "message.userlocked".equals(exception.getMessage())) {
//	        	return errorMessage;
//	        }
//
//	        String remainingAttempts = userPasswordPolicy.getRemainingAttempt();
//	        String message = messages.getMessage("message.badCredentials.warning",
//	                new Object[] { remainingAttempts, userPasswordPolicy.getAllowedAttempt() }, locale) + "</center>";
//
//	        errorMessage = messages.getMessage("label.failed.attempt", null, remainingAttempts, locale);
//	        String remainingAttemptValue1 = messages.getMessage(remainingAttempts, null, remainingAttempts, locale);
//	        String errorMessageIncorrectAttempt = messages.getMessage("label.failed.attempt.second", null,
//	                remainingAttempts, locale);
//
//	        if ("1".equals(remainingAttempts)) {
//	            errorMessage = messages.getMessage("message.badCredentials.last.warning", null, remainingAttempts, locale)
//	                    + "</center>";
//	            errorMessage = remainingAttempts;
//	            errorMessage = messages.getMessage("label.failed.attempt", null, remainingAttempts, locale);
//	            remainingAttemptValue1 = messages.getMessage(remainingAttempts, null, remainingAttempts, locale);
//	            errorMessageIncorrectAttempt = messages.getMessage("label.failed.attempt.second", null, remainingAttempts,
//	                    locale);
//	        }
//
//	        userActivityService.startLoginActivity(getUserLoginActivity(userPasswordPolicy.getUlpId(), exception,
//	                request));
//	        captureIncorrectUserCredential(userPasswordPolicy.getUlpId(),request);
//
//	        if (groupId != 1 && !DefaultProfileUtil.isProfileExits(env, "admin")) {
//	            return errorMessage + " " + remainingAttemptValue1 + " " + errorMessageIncorrectAttempt;
//	        } else {
//	            return messages.getMessage("message.badCredentials", null, locale);
//	        }
//	    }
//
//	    userActivityService.startLoginActivity(getUserLoginActivity(null, exception, request));
//	    return errorMessage;
//	}
//
//	private void captureIncorrectUserCredential(Long ulpId, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		EfmDetailDTO efmDto = new EfmDetailDTO();
//		HttpSession session =request.getSession();
//		Object preLoginObj = session.getAttribute(PRE_LOGIN);
//		if (preLoginObj != null) {
//			PreLoginDTO preLoginDTO = (PreLoginDTO) preLoginObj;
//			if (!DefaultProfileUtil.isProfileExits(env, "admin")) {
//				if (ulpId != null) {
//					efmDto.setUserId(ulpId);
//					efmDto.setSessionId("-");
//					efmDto.setChannel("BIB");
//					efmDto.setDeviceId(efmDto.getChannel().charAt(0) + "- " + preLoginDTO.getTrackingId());
//					efmDto.setDeviceIpAddress(InetUtil.realClientIP(InetUtil.getClientIP(request)).toString());
//					efmDto.setEventType("Incorrect_Credential");
//					efmDto.setRequestType("EFM_EVENT");
//					efmClientService.getDetails(efmDto);
//				}
//			}
//		}
//
//	}
//
//	private UserLoginActivityDTO getUserLoginActivity(Long ulpId, AuthenticationException ex,
//			HttpServletRequest request) {
//		String ipAddress = request.getHeader("X-Forwarded-For");
//		String ip = InetUtil.getClientIP(request);
//		BadCredentialsException bEx = (BadCredentialsException) ex;
//		UserLoginActivityDTO userLoginActivityDTO = new UserLoginActivityDTO();
//		userLoginActivityDTO.setFingerPrint(bEx.getFingerPrint());
//		userLoginActivityDTO.setGroupId(bEx.getGroupId());
//		userLoginActivityDTO.setUserLoginProfileId(ulpId);
//		userLoginActivityDTO.setUsername(bEx.getUsername());
//		userLoginActivityDTO.setChannel("WEB");
//		userLoginActivityDTO.setSessionId(request.getSession().getId());
//		userLoginActivityDTO.setIpAddress(null != ipAddress ? ipAddress : ip);
//		userLoginActivityDTO.setUserAgent(getUserAgentDetail(request));
//		userLoginActivityDTO.setSuccessLogin(false);
//
//		// store it in session so that the handler can use it. see code in
//		// CorporateLoginAuthHandler
//		request.getSession().setAttribute("LOGIN_ACIVITY_DTO", userLoginActivityDTO);
//
//		return userLoginActivityDTO;
//	}
//
//	private String getUserAgentDetail(HttpServletRequest request) {
//		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
//		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
//		return agent.getOperatingSystem().getName() + " / " + agent.getName() + "-"
//				+ agent.getVersionNumber().getMajor() + "." + agent.getVersionNumber().getMinor();
//	}
//
//}