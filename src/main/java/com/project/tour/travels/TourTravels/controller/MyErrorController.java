package com.project.tour.travels.TourTravels.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyErrorController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

	@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
	public String handleError(HttpServletRequest request) {
		Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null && request != null) {
			logger.debug("Error handling : {} for URI {}", status.toString(), request.getRequestURI());
		}
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				logger.info("Page not found : {}", request.getRequestURI());
				return "/error/404";
			} else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				logger.info("Method Not Allowed : {}", request.getRequestURI());
				return "/error/405";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				logger.info("Internal Error : {}", request.getRequestURI());
				return "/error/500";
			} else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
				logger.info("Bad Request : {}", request.getRequestURI());
				return "/error/500";
			}
		}
		return "/error";
	}
}
