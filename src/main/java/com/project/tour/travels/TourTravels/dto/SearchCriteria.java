package com.project.tour.travels.TourTravels.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class SearchCriteria extends AbstractDTO {

	private static final long serialVersionUID = 943823566537912791L;

	private Long status;

	private String value;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fromDate = LocalDate.now().minusDays(30);
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate toDate = LocalDate.now();
	private String user;
	private String reportName;

	public SearchCriteria() {
		super();
	}

	public SearchCriteria(Long status, String value) {
		super();
		this.status = status;
		this.value = value;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}