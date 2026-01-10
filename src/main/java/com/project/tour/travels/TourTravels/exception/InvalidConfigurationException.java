package com.project.tour.travels.TourTravels.exception;

import org.springframework.http.HttpStatus;

public class InvalidConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 7737721593666106168L;

	private HttpStatus statusCode;
	private Boolean convertedMessage = false;

	public InvalidConfigurationException() {
		super();
	}

	public InvalidConfigurationException(String msg) {
		super(msg);
	}

	public InvalidConfigurationException(HttpStatus statusCode, String msg) {
		super(msg);
		this.statusCode = statusCode;

	}

	public InvalidConfigurationException(HttpStatus statusCode, String msg, Boolean convertedMessage) {
		super(msg);
		this.statusCode = statusCode;
		this.convertedMessage = convertedMessage;
	}

	public InvalidConfigurationException(String msg, Boolean convertedMessage) {
		super(msg);
		this.convertedMessage = convertedMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public Boolean getConvertedMessage() {
		return convertedMessage;
	}

	public void setConvertedMessage(Boolean convertedMessage) {
		this.convertedMessage = convertedMessage;
	}

}
