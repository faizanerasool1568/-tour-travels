package com.project.tour.travels.TourTravels.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AbstractValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return false;
	}

	@Override
	public void validate(Object paramObject, Errors paramErrors) {

	}

	public Boolean isTextWithSpaces(String text) {
		String pattern = "^[a-zA-Z_ ]*$";
		if (text.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isTextWithSpacesHypen(String text) {
		// Updated pattern to allow a-z, A-Z, _, -, and spaces
		String pattern = "^[a-zA-Z_ -]*$";

		if (text.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isTextWithOutSpaces(String text) {
		String pattern = "^[a-zA-Z_]*$";
		if (text.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isNumber(String number) {
		String pattern = "^[0-9]*$";
		if (number.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isAplhaNumeric(String number) {
		String pattern = "^[a-zA-Z0-9_ ]*$";
		if (number.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAplhaNumericWithSpaces(String number) {
		String pattern = "^[a-zA-Z0-9_ ]*$";
		if (number.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isValidNumberAndLength(String number, Integer maxlength) {
		String pattern = "^[0-9]+(\\.[0-9]+)?$";
		if (number.matches(pattern)) {
			String numberWithoutDot = number.replace(".", "");

			if (numberWithoutDot.length() >= 1 && numberWithoutDot.length() <= maxlength) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public Boolean isAlphaNumericWithDot(String number) {
		String pattern = "^[a-zA-Z0-9_. ]*$";
		if (number.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isAlphaNumericWithoutSpace(String number) {
		String pattern = "^[a-zA-Z0-9_]*$";
		if (number.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public void validate(Object obj, Errors err, boolean isCreate) {
		// TODO Auto-generated method stub
		
	}

}
