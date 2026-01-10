package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "INF_CONFIGURATION_VALUE")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "INF_CONFIGURATION_VALUE_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class ConfigurationValue extends AbstractEntity {
	
	private static final long serialVersionUID = -2600136362917470259L;

	@Column(name = "CODE", nullable = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "CONFIGURATION_TYPE_ID", nullable = false)
	private ConfigurationType configurationType;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "ENABLED")
	private String enabled;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "EXTRA_INFO")
	private String extraInfo;
	
	@Column(name = "ORDER_BY")
	private String orderBy;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ConfigurationType getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(ConfigurationType configurationType) {
		this.configurationType = configurationType;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}