package com.project.tour.travels.TourTravels.config;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.project.tour.travels.TourTravels.dto.ErrorVM;
import com.project.tour.travels.TourTravels.dto.FieldErrorVM;

@Component
public class ValidationUtils {

	public static final String ERR_VALIDATION = "error.validation";

	public List<FieldErrorVM> processFieldErrors(List<FieldError> fieldErrors) {
		ErrorVM dto = new ErrorVM(ERR_VALIDATION);

		for (FieldError fieldError : fieldErrors) {
			dto.addError(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode(), fieldError.getCode());
		}

		return (List<FieldErrorVM>) dto.getFieldErrors();
	}
}