package com.project.tour.travels.TourTravels.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.tour.travels.TourTravels.dto.LoginRequestDto;
import com.project.tour.travels.TourTravels.service.AdminLoginServiceImpl;

@RestController
public class AdminLoginRestController {
	
	@Autowired
	private AdminLoginServiceImpl serviceImpl;

	@GetMapping("/login")
	public String login(@RequestBody LoginRequestDto loginRequestDto) {
		return serviceImpl.login(loginRequestDto);
	}

}
