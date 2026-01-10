package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "USER_LOGIN_PROFILE")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "USER_LOGIN_PROFILE_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserLoginProfile extends AbstractEntity {

	private static final long serialVersionUID = -2600136362917470259L;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	@Column(name = "USER_ID", nullable = false)
	private String userId;

	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

	@Column(name = "CREATED_BY", nullable = false)
	private Long createdBy;

	@Column(name = "MODIFIED_BY")
	private Long modifiedBy;

	@Column(name = "LAST_SUCCESS_LOGIN")
	private LocalDateTime lastSuccessLogin;

	@Column(name = "LAST_FAILURE_LOGIN")
	private LocalDateTime lastFailureLogin;

	@OneToOne
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private ConfigurationValue status;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "CUSTOMER_PROFILE_ID", nullable = false)
	private UserProfileInfo UserProfileInfo;

	// @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "USER_TYPE", nullable = false)
	private String userTypeInfo;

	@OneToMany(mappedBy = "userLoginProfileId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserAccessRight> services = new ArrayList<>();

	@Column(name = "FAILURE_COUNTER")
	private Long failureCounter;

	@Column(name = "LOCKED_REASON")
	private String lockedReason;

	@Column(name = "LOCKED_DATETIME")
	private LocalDateTime lockedDateTime;

	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToOne
	@JoinColumn(name = "REFER_BY_ID")
	private ConfigurationValue referById;

	@OneToOne
	@JoinColumn(name = "DAILY_COMMUTER_ID")
	private ConfigurationValue dailyCommuterId;

	@Column(name = "COMMENTS")
	private String comments;

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getLastSuccessLogin() {
		return lastSuccessLogin;
	}

	public void setLastSuccessLogin(LocalDateTime lastSuccessLogin) {
		this.lastSuccessLogin = lastSuccessLogin;
	}

	public LocalDateTime getLastFailureLogin() {
		return lastFailureLogin;
	}

	public void setLastFailureLogin(LocalDateTime lastFailureLogin) {
		this.lastFailureLogin = lastFailureLogin;
	}

	public ConfigurationValue getStatus() {
		return status;
	}

	public void setStatus(ConfigurationValue status) {
		this.status = status;
	}

	public UserProfileInfo getUserProfileInfo() {
		return UserProfileInfo;
	}

	public void setUserProfileInfo(UserProfileInfo userProfileInfo) {
		UserProfileInfo = userProfileInfo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserTypeInfo() {
		return userTypeInfo;
	}

	public void setUserTypeInfo(String userTypeInfo) {
		this.userTypeInfo = userTypeInfo;
	}

	public List<UserAccessRight> getServices() {
		return services;
	}

	public void setServices(List<UserAccessRight> services) {
		this.services = services;
	}

	public Long getFailureCounter() {
		return failureCounter;
	}

	public void setFailureCounter(Long failureCounter) {
		this.failureCounter = failureCounter;
	}

	public String getLockedReason() {
		return lockedReason;
	}

	public void setLockedReason(String lockedReason) {
		this.lockedReason = lockedReason;
	}

	public LocalDateTime getLockedDateTime() {
		return lockedDateTime;
	}

	public void setLockedDateTime(LocalDateTime lockedDateTime) {
		this.lockedDateTime = lockedDateTime;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ConfigurationValue getReferById() {
		return referById;
	}

	public void setReferById(ConfigurationValue referById) {
		this.referById = referById;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ConfigurationValue getDailyCommuterId() {
		return dailyCommuterId;
	}

	public void setDailyCommuterId(ConfigurationValue dailyCommuterId) {
		this.dailyCommuterId = dailyCommuterId;
	}

}