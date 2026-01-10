package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INF_USERS_ACTIVITIES")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "INF_USERS_ACTIVITIES_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserActivityLog extends AbstractEntity {

	@Column(name = "SERVICE_ID", nullable = false)
	private Long userService;

	@ManyToOne
	@JoinColumn(name = "LOGIN_ACTIVITY_ID")
	private UserLoginActivity userLoginActivity;

	@Column(name = "ACTIVITY_DATETIME", nullable = false)
	private LocalDateTime activityDateTime;

	@Column(name = "REFERENCE_NUMBER")
	private String referenceNumber;

	@Column(name = "ACTIVITY_DESCRIPTION")
	private String activityDescription;

	@Column(name = "USER_ACTION")
	private String userAction;

	public Long getUserService() {
		return userService;
	}

	public void setUserService(Long userService) {
		this.userService = userService;
	}

	public UserLoginActivity getUserLoginActivity() {
		return userLoginActivity;
	}

	public void setUserLoginActivity(UserLoginActivity userLoginActivity) {
		this.userLoginActivity = userLoginActivity;
	}

	public LocalDateTime getActivityDateTime() {
		return activityDateTime;
	}

	public void setActivityDateTime(LocalDateTime activityDateTime) {
		this.activityDateTime = activityDateTime;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

}