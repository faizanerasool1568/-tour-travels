package com.project.tour.travels.TourTravels.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

	private SecurityUtils() {
	}

	public static void invalidateSession(HttpSession session) {
		if (SecurityUtils.isAuthenticated()) {
			SecurityUtils.getCurrentUser().setJwtToken(null);
		}
		SecurityContextHolder.clearContext();
	}

	public static void invalidateSession(HttpServletRequest request) {
		invalidateSession(request.getSession());
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static String getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = null;
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				userName = (String) authentication.getPrincipal();
			}
		}
		return userName;
	}

	public static String getMDCUserName() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		return "[" + authentication.getPrincipal().toString() + "]";
	}

	public static MyUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MyUser myUser = ((MyUser) principal);
		return myUser;
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * Check if a user is authenticated.
	 *
	 * @return true if the user is authenticated, false otherwise
	 */
	public static boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext.getAuthentication() != null) {
			Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
			if (authorities != null) {
				for (GrantedAuthority authority : authorities) {
					if (authority.getAuthority().equals("ROLE_ANONYMOUS")) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static Long getCurrentUserLoginProfileId() {
		return getCurrentUser().getId();
	}

	/**
	 * If the current user has a specific authority (security role).
	 *
	 * <p>
	 * The name of this method comes from the isUserInRole() method in the Servlet
	 * API
	 * </p>
	 *
	 * @param authority the authority to check
	 * @return true if the current user has the authority, false otherwise
	 */
	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
			}
		}
		return false;
	}

	public static boolean isCurrentUserHasAccessToAnyOneAccount(String authority) {
		Boolean isAccessAllowed = Boolean.FALSE;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				for (GrantedAuthority userAuthority : springSecurityUser.getAuthorities())
					if (userAuthority.getAuthority().contains(authority)) {
						isAccessAllowed = Boolean.TRUE;
						break;
					}
			}
		}
		return isAccessAllowed;
	}

	public Boolean isMobile() {
		return !SecurityUtils.getCurrentUser().getTrackingId().startsWith("W-");
	}

	public Boolean isWeb() {
		return SecurityUtils.getCurrentUser().getTrackingId().startsWith("W-");
	}

	/*
	 * Used for Initiator and Approver Info With User Login and Date with Time
	 */
	public static String getUserDetailInfo() {
		return getCurrentUserLogin() + "-"
				+ (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

}
