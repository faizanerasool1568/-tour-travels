package com.project.tour.travels.TourTravels.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -3865328250761933720L;
	public static final String ERR_VALIDATION = "error.validation";

	private List<FieldErrorVM> fieldErrors = new ArrayList<>();

	public BusinessException(AbstractDTO dto, String fieldName, String message) {

		super(message);
		addError(dto, fieldName, message);
	}

	public BusinessException(List<FieldErrorVM> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	/**
	 * Used internally by another exception. use other available constructors
	 * 
	 * @param errorCode
	 * @param dto
	 * @param fieldName
	 * @param message
	 */
	public BusinessException(String errorCode, AbstractDTO dto, String fieldName, String message) {
		super(message);
		addErrorInternal(errorCode, dto, fieldName, message);
	}

	public BusinessException(String errorCode, AbstractDTO dto, String fieldName, String message, Object... params) {
		super(message);
		addErrorInternal(errorCode, dto, fieldName, message, params);
	}

	public void addError(AbstractDTO dto, String fieldName, String message) {
		addErrorInternal(ERR_VALIDATION, dto, fieldName, message);
	}

	private void addErrorInternal(String errorCode, AbstractDTO dto, String fieldName, String message) {
		this.fieldErrors.add(new FieldErrorVM(errorCode, dto.getClass().getSimpleName(), fieldName, message));
	}

	private void addErrorInternal(String errorCode, AbstractDTO dto, String fieldName, String message,
			Object... params) {
		this.fieldErrors.add(new FieldErrorVM(errorCode, dto.getClass().getSimpleName(), fieldName, message, params));
	}

	public List<FieldErrorVM> getFieldErrors() {
		return fieldErrors;
	}

	public Errors addServerSideErrors(BindingResult errors) {
		for (FieldErrorVM errorVM : fieldErrors) {
			errors.rejectValue(errorVM.getField(), errorVM.getMessage());
		}
		return errors;
	}

	public void addPrefixToFieldErrors(String prefix) {
		for (FieldErrorVM fieldErrorVM : this.getFieldErrors()) {
			fieldErrorVM.setField(prefix + fieldErrorVM.getField());
		}
	}

}