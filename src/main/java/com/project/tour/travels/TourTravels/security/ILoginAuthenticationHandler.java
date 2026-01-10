package com.project.tour.travels.TourTravels.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface ILoginAuthenticationHandler {
	void onAuthenticationSuccessHook(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication);

	void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException;

	String getUserLoginSecurity(Long ulpId);
}
