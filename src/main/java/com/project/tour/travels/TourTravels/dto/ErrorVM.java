package com.project.tour.travels.TourTravels.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * View Model for transferring error message with a list of field errors.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorVM implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private String description;
	private String developerMessage;
	private String[] params;
	private Boolean convertedMessage = false;

	public ErrorVM() {
		super();
	}

	private List<FieldErrorVM> fieldErrors;

	public ErrorVM(String message) {
		this(message, null, null, null);
	}

	public ErrorVM(String message, String description, String... params) {
		this.message = message;
		this.description = description;
		this.developerMessage = null;
		this.params = params;
	}

	public void addError(String objectName, String field, String code, String message, Object... params) {
		if (fieldErrors == null) {
			fieldErrors = new ArrayList<>();
		}
		fieldErrors.add(new FieldErrorVM(code, objectName, field, message));
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public List<FieldErrorVM> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorVM> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String[] getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "ErrorVM{" + "message='" + message + '\'' + ", description='" + description + '\''
				+ ", developerMessage='" + developerMessage + '\'' + ", params=" + Arrays.toString(params)
				+ ", fieldErrors=" + fieldErrors + '}';
	}

	public Boolean getConvertedMessage() {
		return convertedMessage;
	}

	public void setConvertedMessage(Boolean convertedMessage) {
		this.convertedMessage = convertedMessage;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}