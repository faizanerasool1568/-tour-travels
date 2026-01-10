package com.project.tour.travels.TourTravels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.tour.travels.TourTravels.dto.LoginRequestDto;
import com.project.tour.travels.TourTravels.rest.controller.AdminLoginRestController;

@Controller
//@RequestMapping("/prelogin")
public class AdminLoginController {

	@Autowired
	private AdminLoginRestController loginRestController;

	@GetMapping("/prelogin/home")
	public String preLogin(Model model) {
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		model.addAttribute("preLogin", loginRequestDto);
		// String res = loginRestController.login(loginRequestDto);
		return "login/login";
	}

	@GetMapping("/prelogin/validateLogin")
	public String validate(Model model) {
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		model.addAttribute("preLogin", loginRequestDto);
		String res = loginRestController.login(loginRequestDto);
		return "login/login";
	}

	@GetMapping("/login/home")
	public String login(Model model) {
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		model.addAttribute("preLogin", loginRequestDto);
		// String res = loginRestController.login(loginRequestDto);
		return "login/home";
	}

}
