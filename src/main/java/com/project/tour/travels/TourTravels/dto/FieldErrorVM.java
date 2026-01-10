package com.project.tour.travels.TourTravels.dto;

import java.io.Serializable;

public class FieldErrorVM implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String objectName;
	private String field;
	private String message;
	private Object[] params;

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public FieldErrorVM() {
		super();
	}

	public FieldErrorVM(String code, String objectName, String field, String message) {
		this.objectName = objectName;
		this.code = code;
		this.field = field;
		this.message = message;
	}

	public FieldErrorVM(String code, String objectName, String field, String message, Object[] params) {
		this(code, objectName, field, message);
		this.params = params;
	}

	public String getObjectName() {
		return objectName;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "field : " + this.field + ", message : " + this.message;
	}
}