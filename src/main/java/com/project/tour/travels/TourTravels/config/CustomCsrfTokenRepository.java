package com.project.tour.travels.TourTravels.config;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.util.Assert;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {
	private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
	private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
	private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class.getName()
			.concat(".CSRF_TOKEN");
	private String parameterName = "_csrf";
	private String headerName = "X-CSRF-TOKEN";
	private String sessionAttributeName;

	public CustomCsrfTokenRepository() {
		this.sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;
	}

	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session;
		if (token == null) {
			session = request.getSession(false);
			if (session != null) {
				session.removeAttribute(this.sessionAttributeName);
			}
		} else {
			session = request.getSession();
			session.setAttribute(this.sessionAttributeName, token);
		}

	}

	public CsrfToken loadToken(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session == null ? null : (CsrfToken) session.getAttribute(this.sessionAttributeName);
	}

	public CsrfToken generateToken(HttpServletRequest request) {
		return new DefaultCsrfToken(this.headerName, this.parameterName, this.createNewToken());
	}

	public void setParameterName(String parameterName) {
		Assert.hasLength(parameterName, "parameterName cannot be null or empty");
		this.parameterName = parameterName;
	}

	public void setHeaderName(String headerName) {
		Assert.hasLength(headerName, "headerName cannot be null or empty");
		this.headerName = headerName;
	}

	public void setSessionAttributeName(String sessionAttributeName) {
		Assert.hasLength(sessionAttributeName, "sessionAttributename cannot be null or empty");
		this.sessionAttributeName = sessionAttributeName;
	}

	private String createNewToken() {
		return "C-" + UUID.randomUUID().toString() + "-" + System.nanoTime();
	}
}
