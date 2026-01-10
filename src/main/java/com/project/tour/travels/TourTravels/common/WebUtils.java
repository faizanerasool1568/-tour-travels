package com.project.tour.travels.TourTravels.common;

import java.security.SecureRandom;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.project.tour.travels.TourTravels.dto.PageWrapperDTO;

@Component
public class WebUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

	public static int generateRandom(int min, int max) {
		SecureRandom random = new SecureRandom();
		return random.nextInt(max - min + 1) + min;
	}

	public static Long ramdom3DigitNumber() {
		SecureRandom secRandom = new SecureRandom();
		return new Long(secRandom.nextInt(900) + 100);
	}

	public static <T> void setPageAndLinks(PageWrapperDTO<T> searchResultPage, ModelMap model) {
		if (searchResultPage == null)
			return;
		Page<T> page = searchResultPage.getPage();
		model.addAttribute("page", page);
		// kamal-2022-12-21-CR-Payment-report-offline-beg
		model.addAttribute("infoMessage", searchResultPage.getInfoMessage());
		model.addAttribute("errorMessage", searchResultPage.getErrorMessage());
		// kamal-2022-12-21-CR-Payment-report-offline-end
		if (page != null) {
			LOGGER.info("Found {} entries. Returned page {} contains {}  entries", page.getTotalElements(),
					page.getNumber(), page.getNumberOfElements());
		} else {
			LOGGER.info("Found {} entries. Returned page {} contains {}  entries", 0, 0, 0);
		}
	}

	public static String getChannel(ModelMap modelMap) {
		return modelMap.get("channel").toString();
	}

	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent") != null ? request.getHeader("User-Agent") : "unknown";
	}

	public static void setPasswordRemainder(HttpServletRequest request, ModelMap model, MessageSource resourceBundle,
			Locale locale) {
		// check MyAuthenticationSuccessHandler code for this session attribute
		Object pwdReminderDays = request.getSession().getAttribute("password-remainder-days");
		if (pwdReminderDays != null) {
			if ("0".equals(pwdReminderDays)) {
				model.addAttribute("pwdRemainderDays",
						resourceBundle.getMessage("label.pwd.remainder.immediate", null, locale));
				model.addAttribute("alerttype", "error");
			} else if (!"-1".equals(pwdReminderDays)) {
				String[] args = { (String) pwdReminderDays };
				model.addAttribute("pwdRemainderDays",
						resourceBundle.getMessage("label.pwd.remainder.ndays", args, locale));
				model.addAttribute("alerttype", "warning");
			}
			LOGGER.info("pwdRemainderDays :: {}", pwdReminderDays);
			request.getSession().removeAttribute("password-remainder-days");
		}
	}

}