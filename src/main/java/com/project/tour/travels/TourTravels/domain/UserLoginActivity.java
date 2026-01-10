package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_LOGIN_ACTIVITY")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "USER_LOGIN_ACTIVITY_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserLoginActivity extends AbstractEntity {

	private static final long serialVersionUID = 7665864750642742134L;

	@ManyToOne
	@JoinColumn(name = "LOGIN_PROFILE_ID")
	private UserLoginProfile userLoginProfile;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "USER_AGENT_STRING")
	private String userAgentString;

	@Column(name = "DEVICE_FINGER_PRINT")
	private String deviceFingerPrint;

	@Column(name = "SESSION_ID")
	private String sessionId;

	@Column(name = "TRACKING_ID")
	private String trackingId;

	@Column(name = "START_DATETIME")
	private LocalDateTime startDatetime = LocalDateTime.now();

	@Column(name = "END_DATETIME")
	private LocalDateTime endDatetime;

	@Column(name = "CHANNEL", nullable = false)
	private String channel;

	public UserLoginProfile getUserLoginProfile() {
		return userLoginProfile;
	}

	public void setUserLoginProfile(UserLoginProfile userLoginProfile) {
		this.userLoginProfile = userLoginProfile;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserAgentString() {
		return userAgentString;
	}

	public void setUserAgentString(String userAgentString) {
		this.userAgentString = userAgentString;
	}

	public String getDeviceFingerPrint() {
		return deviceFingerPrint;
	}

	public void setDeviceFingerPrint(String deviceFingerPrint) {
		this.deviceFingerPrint = deviceFingerPrint;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDateTime getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(LocalDateTime startDatetime) {
		this.startDatetime = startDatetime;
	}

	public LocalDateTime getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(LocalDateTime endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}