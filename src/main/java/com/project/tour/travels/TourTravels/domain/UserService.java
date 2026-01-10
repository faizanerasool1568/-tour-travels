package com.project.tour.travels.TourTravels.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FND_SERVICE")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "FND_SERVICE_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserService extends AbstractEntity {

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "APPLICABLE_FOR", nullable = false)
	private String applicableFor;

	@OneToOne
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private ConfigurationValue status;

	public ConfigurationValue getStatus() {
		return status;
	}

	public void setStatus(ConfigurationValue status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplicableFor() {
		return applicableFor;
	}

	public void setApplicableFor(String applicableFor) {
		this.applicableFor = applicableFor;
	}

}