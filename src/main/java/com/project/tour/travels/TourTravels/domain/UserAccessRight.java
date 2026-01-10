package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FND_USER_ACCESS_RIGHT")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "FND_USER_ACCESS_RIGHT_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserAccessRight extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "USER_PROFILE_ID")
	private UserLoginProfile userLoginProfileId;

	@ManyToOne
	@JoinColumn(name = "SERVICE_ID")
	private UserService userService;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

	@OneToOne
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private ConfigurationValue status;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "MODIFIED_BY")
	private Long modifiedBy;

	public ConfigurationValue getStatus() {
		return status;
	}

	public void setStatus(ConfigurationValue status) {
		this.status = status;
	}

	public UserLoginProfile getUserLoginProfileId() {
		return userLoginProfileId;
	}

	public void setUserLoginProfileId(UserLoginProfile userLoginProfileId) {
		this.userLoginProfileId = userLoginProfileId;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

}