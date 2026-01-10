//package com.project.tour.travels.TourTravels.config;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//
//@Component
//public class TokenProvider {
//
//	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
//
//	private static final String AUTHORITIES_KEY = "auth";
//
//	public static final String USER_ACTIVITY_END = "/end";
//
//	public static final String USER_SESSION_CHECK = "/check-user-session";
//
//	private String secretKey;
//
//	private long tokenValidityInSeconds;
//
//	private long tokenValidityInSecondsForRememberMe;
//
//	private @Autowired CacheManager cacheManager;
//
//	//private @Autowired UserActivityServiceImpl userActivityService;
//
//	//@Autowired
//	//private AppProperties appProperties;
//
////	private @Autowired UserMapper userMapper;
////
////	private @Autowired FeatureManager featureManager;
////	private @Autowired ProducerTemplate producerTemplate;
//
//	@PostConstruct
//	public void init() {
//		this.secretKey = appProperties.getSecurity().getAuthentication().getJwt().getSecret();
//		this.tokenValidityInSeconds = 1000
//				* appProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
//		this.tokenValidityInSecondsForRememberMe = 1000
//				* appProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
//	}
//
//	public String createToken(Authentication authentication, Boolean rememberMe) {
//		String authorities = "";
//
//		authorities = authentication.getAuthorities().size() > 0 ? authentication.getAuthorities().stream()
//				.map(authority -> authority.getAuthority()).collect(Collectors.joining(",")) : "ROLE_USER";
//
//		long now = (new Date()).getTime();
//		Date validity;
//		if (rememberMe) {
//			validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
//		} else {
//			validity = new Date(now + this.tokenValidityInSeconds);
//		}
//
//		MyUser myUser = (MyUser) authentication.getPrincipal();
//
//		Map<String, Object> customClaimsMap = new HashMap<String, Object>();
//		customClaimsMap.put(AUTHORITIES_KEY, authorities);
//		customClaimsMap.put("ID", myUser.getId());
//		customClaimsMap.put("LANG", myUser.getLanguage());
//		customClaimsMap.put("NAME", myUser.getFullname());
//		customClaimsMap.put("TID", myUser.getTrackingId());
//		customClaimsMap.put("SID", myUser.getSessionId());
//		customClaimsMap.put("2FA", myUser.getTransSecurityType());
//		if (myUser.getGroupId() != null) {
//			customClaimsMap.put("GROUP_ID", myUser.getGroupId());
//		}
//		return Jwts.builder().setClaims(customClaimsMap).setSubject(authentication.getName())
//				.signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity).compact();
//	}
//
//	public Authentication getAuthentication(String token, String lang) {
//		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//
//		Collection<? extends GrantedAuthority> authorities = Arrays
//				.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
//				.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
//
//		MyUser principal = new MyUser(claims.getSubject(), "", authorities);
//		principal.setFullname((String) claims.get("NAME"));
//		principal.setId(Integer.valueOf((Integer) claims.get("ID")).longValue());
//		principal.setLanguage(lang != null ? lang : "en_US");
//		principal.setTransSecurityType((String) claims.get("2FA"));
//
//		if (claims.get("GROUP_ID") != null) {
//			Integer groupId = (Integer) claims.get("GROUP_ID");
//			if (groupId != null) {
//				principal.setGroupId(groupId.longValue());
//			}
//		}
//		principal.setTrackingId(claims.get("TID").toString());
//		principal.setSessionId(claims.get("SID").toString());
//		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
//	}
//
//	public void updateRecentActivityTime() {
//		String trackingId = SecurityUtils.getCurrentUser().getTrackingId();
//		userMapper.updateUserLoginActivity(trackingId, LocalDateTime.now());
//	}
//
//	public void clearExistingActiveUserSession(HttpServletRequest request) {
//		if (SecurityUtils.isAuthenticated()) {
//			Long ulpId = SecurityUtils.getCurrentUserLoginProfileId();
//			String trackingId = SecurityUtils.getCurrentUser().getTrackingId();
//			IdentityDTO identityDTO = new IdentityDTO(ulpId);
//			Map<String, String> map = new HashMap<>();
//			map.put("TRACKING_ID", trackingId);
//			identityDTO.setExtras(map);
//			Long aLong = userActivityService.checkUserBySessionIdByTrackingId(identityDTO);
//			log.debug("TokenProvider : already active user count {} for ulp {} trackingId  {}", aLong, ulpId,
//					trackingId);
//			if (aLong != null && aLong > 0) {
//				List<CommonDetailVO> existingUser = userActivityService.getUserSessionIds(new IdentityDTO(ulpId));
//				HttpServletRequest rq = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//						.getRequest();
//				if (!CollectionUtils.isEmpty(existingUser) && existingUser.size() > 0) {
//					// for efm record in 8 lines
//					EfmDetailDTO dto = new EfmDetailDTO();
////					dto =getCache(SecurityUtils.getCurrentUser().getTrackingId()+"-TXN", EfmDetailDTO.class);
//					if (!SecurityUtils.getCurrentUser().isUsingMobile()) {
//						log.info("rq.getRequestURI():::: " + rq.getRequestURI());
//						if (rq.getRequestURI().contains(USER_ACTIVITY_END)
//								|| rq.getRequestURI().contains(USER_SESSION_CHECK)) {
//							dto.setEventType("LOGOUT");
//							dto.setSessionId(SecurityUtils.getCurrentUser().getSessionId());
//							dto.setDeviceId(SecurityUtils.getCurrentUser().getTrackingId());
//							dto.setDeviceIpAddress(InetUtil.realClientIP(InetUtil.getClientIP(request)));
//							dto.setRequestType("EFM_EVENT");
//							dto.setMode("Another session Opened");
//							producerTemplate.sendBody("direct:efmDetailSession", dto);
//						}
//					}
//					if (SecurityUtils.getCurrentUser().isUsingMobile()) {
//						dto.setEventType("LOGOUT");
//						dto.setSessionId(SecurityUtils.getCurrentUser().getSessionId());
//						dto.setDeviceId(SecurityUtils.getCurrentUser().getTrackingId());
//						dto.setDeviceIpAddress(InetUtil.realClientIP(InetUtil.getClientIP(request)));
//						dto.setRequestType("EFM_EVENT");
//						dto.setMode("Another session Opened");
//						log.info("rq.getRequestURI():::: " + rq.getRequestURI());
//						producerTemplate.sendBody("direct:efmDetailSession", dto);
//					}
//					throw new AccessDeniedException("Access forbidden, No Valid session.");
//				}
//			}
//		}
//	}
//
//	public boolean validateToken(String authToken) {
//		try {
//			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
//			return true;
//		} catch (SignatureException e) {
//			log.info("Invalid JWT signature: " + e.getMessage());
//			return false;
//		}
//	}
//
//	public boolean checkSession(Long ulpId, String trackingId) {
//		Long aLong = userMapper.checkSessionByTrackingId(ulpId, trackingId);
//		log.debug("RestCoordinator : checkSession {}", aLong);
//		return aLong != null && aLong > 0;
//	}
//
//	public String checkUserSession(Long ulpId, String trackingId) {
//		Long aLong = userMapper.checkSessionByTrackingId(ulpId, trackingId);
//		log.debug("RestCoordinator : checkSession {}", aLong);
//		if (aLong != null && aLong > 0) {
//			List<CommonDetailVO> existingUser = userActivityService.getUserSessionIds(new IdentityDTO(ulpId));
//			if (!CollectionUtils.isEmpty(existingUser) && existingUser.size() > 0) {
//				return "Y";
//			}
//			return "S";
//		}
//		return "N";
//	}
//
//	public boolean isValidImagePath(Long ulpId, Long inwardDocumentId) {
//		Long documentId = userMapper.getProfileDocumentIdByUlp(ulpId);
//		if (documentId != null) {
//			return documentId.equals(inwardDocumentId);
//		}
//		return true;
//	}
//
//	public Long getCurrentUserLoginId(String key) {
//		if (SecurityUtils.isAuthenticated()) {
//			return SecurityUtils.getCurrentUserLoginProfileId();
//		} else {
//			return getCache(key, Long.class, "mobileCache");
//		}
//	}
//
//	public <T> T getCache(String key, Class<T> type, String cacheName) {
//		Cache.ValueWrapper valueWrapper = cacheManager.getCache(cacheName).get(key);
//		if (valueWrapper == null) {
//			return null;
//		}
//		return (T) valueWrapper.get();
//	}
//
//	public Long getImageId(Long ulpId) {
//		return userMapper.getProfileDocumentIdByUlp(ulpId);
//	}
//}
