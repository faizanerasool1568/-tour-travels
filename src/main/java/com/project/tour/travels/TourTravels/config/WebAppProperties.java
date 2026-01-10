package com.project.tour.travels.TourTravels.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = true)
public class WebAppProperties {
	private static final Logger logger = LoggerFactory.getLogger(WebAppProperties.class);

	private String apiURL;
	private String loginURL;
	private String defaultLocale = "en_US";
	private String dateFormat = "dd/MM/yyyy";

	private String resourceLocations;
	private String maxEmailCount;
	private String uploadedFilePath = "/home/faizane/tour/upload";
	private String downloadedFilePath = "/home/faizane/tour/download";

	private Integer excelRecordSize = 100;

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	// private final Environment env;
	private final HttpConnPool httpConnPool = new HttpConnPool();

	public HttpConnPool getHttpConnPool() {
		return httpConnPool;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getUploadedFilePath() {
		return uploadedFilePath;
	}

	public void setUploadedFilePath(String uploadedFilePath) {
		this.uploadedFilePath = uploadedFilePath;
	}

	public Integer getExcelRecordSize() {
		return excelRecordSize;
	}

	public void setExcelRecordSize(Integer excelRecordSize) {
		this.excelRecordSize = excelRecordSize;
	}

	public String getDownloadedFilePath() {
		return downloadedFilePath;
	}

	public void setDownloadedFilePath(String downloadedFilePath) {
		this.downloadedFilePath = downloadedFilePath;
	}

	private final Security security = new Security();

	public String validateAndRedirect(String redirectKey) {
		String whitelistUrl = this.security.getWhitelistUrls().get(redirectKey);
		if (whitelistUrl != null) {
			logger.info("redirectKey to: {}", whitelistUrl);
			return "redirect:" + whitelistUrl;
		} else {
			logger.info("redirectKey Not Found: {}", redirectKey);
			return null;
			// throw new SecurityBreachException("UNVALIDATED_REDIRECTS");
		}
	}

	public Security getSecurity() {
		return security;
	}

	public static class HttpConnPool {
		private String protocol = null;
		private Integer maxTotalConnections = 40;
		private Integer maxRouteConnections = 40;
		private Integer maxLocalHostConnections = 80;
		private Integer defaultKeepAliveTime = 20;

		// the time for waiting until a connection is established
		private Integer connectionTimeout = 30;

		// the time for waiting for a connection from connection pool
		private Integer requestTimeout = 30;

		// the time for waiting for data
		private Integer socketTimeout = 60;
		private Integer idleConnectionWaitTime = 30;

		public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}

		public Integer getMaxTotalConnections() {
			return maxTotalConnections;
		}

		public void setMaxTotalConnections(Integer maxTotalConnections) {
			this.maxTotalConnections = maxTotalConnections;
		}

		public Integer getMaxRouteConnections() {
			return maxRouteConnections;
		}

		public void setMaxRouteConnections(Integer maxRouteConnections) {
			this.maxRouteConnections = maxRouteConnections;
		}

		public Integer getMaxLocalHostConnections() {
			return maxLocalHostConnections;
		}

		public void setMaxLocalHostConnections(Integer maxLocalHostConnections) {
			this.maxLocalHostConnections = maxLocalHostConnections;
		}

		public Integer getDefaultKeepAliveTime() {
			return defaultKeepAliveTime * 1000;
		}

		public void setDefaultKeepAliveTime(Integer defaultKeepAliveTime) {
			this.defaultKeepAliveTime = defaultKeepAliveTime;
		}

		public Integer getConnectionTimeout() {
			return connectionTimeout * 1000;
		}

		public void setConnectionTimeout(Integer connectionTimeout) {
			this.connectionTimeout = connectionTimeout;
		}

		public Integer getRequestTimeout() {
			return requestTimeout * 1000;
		}

		public void setRequestTimeout(Integer requestTimeout) {
			this.requestTimeout = requestTimeout;
		}

		public Integer getSocketTimeout() {
			return socketTimeout * 1000;
		}

		public void setSocketTimeout(Integer socketTimeout) {
			this.socketTimeout = socketTimeout;
		}

		public Integer getIdleConnectionWaitTime() {
			return idleConnectionWaitTime * 1000;
		}

		public void setIdleConnectionWaitTime(Integer idleConnectionWaitTime) {
			this.idleConnectionWaitTime = idleConnectionWaitTime;
		}

	}

	public static class Security {
		/* private Integer sessionTimeout = 10; */
		private Integer maximumSessions = 1;
		private Map<String, String> whitelistUrls = new HashMap<String, String>();
		private Boolean skipPwd = false;
		private List<String> allowedHostNames = new ArrayList<>();
		private List<String> skipedSecurityBruteForceAttackUri = new ArrayList<>();
		private List<String> xssAllowedtags = new ArrayList<>();

		private Boolean disableCSRF = false;

		public Boolean getDisableCSRF() {
			return disableCSRF;
		}

		public void setDisableCSRF(Boolean disableCSRF) {
			this.disableCSRF = disableCSRF;
		}

		public List<String> getXssAllowedtags() {
			return xssAllowedtags;
		}

		public void setXssAllowedtags(List<String> xssAllowedtags) {
			this.xssAllowedtags = xssAllowedtags;
		}

		public List<String> getAllowedHostNames() {
			return this.allowedHostNames;
		}

		public void setAllowedHostNames(List<String> allowedHostNames) {
			this.allowedHostNames = allowedHostNames;
		}

		public List<String> getSkipedSecurityBruteForceAttackUri() {
			return skipedSecurityBruteForceAttackUri;
		}

		public void setSkipedSecurityBruteForceAttackUri(List<String> skipedSecurityBruteForceAttackUri) {
			this.skipedSecurityBruteForceAttackUri = skipedSecurityBruteForceAttackUri;
		}

		public Boolean getSkipPwd() {
			return skipPwd;
		}

		public void setSkipPwd(Boolean skipPwd) {
			this.skipPwd = skipPwd;
		}

		/*
		 * public Integer getOtpResend() { return otpResend; }
		 * 
		 * public void setOtpResend(Integer otpResend) { this.otpResend = otpResend; }
		 */

		public Map<String, String> getWhitelistUrls() {
			return whitelistUrls;
		}

		public void setWhitelistUrls(Map<String, String> whitelistUrls) {
			this.whitelistUrls = whitelistUrls;
		}

		public Integer getMaximumSessions() {
			return maximumSessions;
		}

		public void setMaximumSessions(Integer maximumSessions) {
			this.maximumSessions = maximumSessions;
		}

	}

	public String getApiURL(String url) {
		return apiURL + url;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public String getResourceLocations() {
		return resourceLocations;
	}

	public void setResourceLocations(String resourceLocations) {
		this.resourceLocations = resourceLocations;
	}

	public String getApiURL() {
		return apiURL;
	}

	public String getMaxEmailCount() {
		return maxEmailCount;
	}

	public void setMaxEmailCount(String maxEmailCount) {
		this.maxEmailCount = maxEmailCount;
	}
}
