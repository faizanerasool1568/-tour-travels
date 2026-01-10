package com.project.tour.travels.TourTravels.dto;

import java.io.Serializable;

public class UserActivityLogDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3368571341226280514L;

	private String service;
	private String trackingId;
	private String referenceNumber;
	private String userAction;
	private String comments;

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}