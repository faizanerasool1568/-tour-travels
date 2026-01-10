package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INF_CONFIGURATION_TYPE")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "INF_CONFIGURATION_TYPE_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class ConfigurationType extends AbstractEntity {

	private static final long serialVersionUID = -2600136362917470259L;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "ENABLED")
	private String enabled;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "configurationType", cascade = CascadeType.ALL)
	private List<ConfigurationValue> values = new ArrayList<ConfigurationValue>();

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

	public List<ConfigurationValue> getValues() {
		return values;
	}

	public void setValues(List<ConfigurationValue> values) {
		this.values = values;
	}

}