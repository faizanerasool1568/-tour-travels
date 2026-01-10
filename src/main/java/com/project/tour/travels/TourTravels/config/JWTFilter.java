//package com.project.tour.travels.TourTravels.config;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
///**
// * Filters incoming requests and installs a Spring Security principal if a
// * header corresponding to a valid user is found.
// */
//public class JWTFilter extends GenericFilterBean {
//
//	private final Logger log = LoggerFactory.getLogger(JWTFilter.class);
//
//	private TokenProvider tokenProvider;
//
//	private static final String PATH_DOWNLOAD_PATTERN = "/downloads/";
//
//	private static final String PATH_REPORT_PATTERN = "/reports/";
//	private static final String PATH_USER_IMG_PATTERN = "/images/user/";
//
//	public JWTFilter(TokenProvider tokenProvider) {
//		this.tokenProvider = tokenProvider;
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//		try {
//			// servletRequest.get
//			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//			String jwt = resolveToken(httpServletRequest);
//			if (StringUtils.hasText(jwt)) {
//				if (this.tokenProvider.validateToken(jwt)) {
//					Authentication authentication = this.tokenProvider.getAuthentication(jwt,
//							httpServletRequest.getHeader("lang"));
//					SecurityContextHolder.getContext().setAuthentication(authentication);
//					// update user session info
//					String messageCountHeader = httpServletRequest.getHeader("sessionupdate");
//					if (messageCountHeader == null) {
//						messageCountHeader = "Y";
//					}
//					if (messageCountHeader.equals("Y")) {
//						this.tokenProvider.updateRecentActivityTime();
//						this.tokenProvider.clearExistingActiveUserSession(httpServletRequest);
//					}
//
//				}
//			}
//
//			filterChain.doFilter(servletRequest, servletResponse);
//		} catch (ExpiredJwtException eje) {
//			log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
//			((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		} catch (AccessDeniedException e) {
//			// throw new JwtException("No Valid token found");
//			// custom error response class used across my project
//			if (SecurityUtils.getCurrentUser().isUsingMobile()) {
//				RestError rs = new RestError();
//				ErrorWrapper wrapper = new ErrorWrapper();
//				GlobalError globalError = new GlobalError("UNAUTHORIZED", "No Valid Token found");
//				List<GlobalError> errors = new ArrayList<>();
//				errors.add(globalError);
//				wrapper.setGlobalErrors(errors);
//				rs.setErrorWrapper(wrapper);
//				httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//				httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//				httpServletResponse.getWriter().write(convertObjectToJson(rs));
//
//			} else {
//				filterChain.doFilter(servletRequest, servletResponse);
//			}
//		}
//	}
//
//	public String convertObjectToJson(Object object) throws JsonProcessingException {
//		if (object == null) {
//			return null;
//		}
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.writeValueAsString(object);
//	}
//
//	private String resolveToken(HttpServletRequest request) {
//		String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
//		// log.debug("resolveToken : bearerToken {}",bearerToken);
//		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7);
//		}
//		String jwt = request.getParameter(JWTConfigurer.AUTHORIZATION_TOKEN);
//		if (StringUtils.hasText(jwt)) {
//			return jwt;
//		}
//		return null;
//	}
//
//	public boolean isNumeric(String strNum) {
//		if (strNum == null) {
//			return false;
//		}
//		try {
//			double d = Double.parseDouble(strNum);
//		} catch (NumberFormatException nfe) {
//			return false;
//		}
//		return true;
//	}
//
//}
