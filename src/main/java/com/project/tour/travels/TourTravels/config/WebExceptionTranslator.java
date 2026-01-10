package com.project.tour.travels.TourTravels.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.tour.travels.TourTravels.dto.BusinessException;
import com.project.tour.travels.TourTravels.dto.ErrorVM;

@ControllerAdvice
public class WebExceptionTranslator {
	private static final Logger logger = LoggerFactory.getLogger(WebExceptionTranslator.class);

	private @Autowired Environment env;
	private @Autowired MessageSource resourceBundle;
	private @Autowired WebAppProperties props;
	public static final String ERR_VALIDATION = "error.validation";

	public static String getFullURL(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();

		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}

	@ModelAttribute("myLang")
	public String myLang(Model model, HttpSession session, HttpServletRequest request) {
		// logger.debug("req :: {}", getFullURL(request));
		model.addAttribute("contextRoot", request.getContextPath() + "/");
		return "";
	}

	@ExceptionHandler({ BusinessException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorVM handleBusinessException(BusinessException ex, Locale locale) {
		ErrorVM error = new ErrorVM(ERR_VALIDATION);
		error.setFieldErrors(ex.getFieldErrors());
		return error;
	}

}