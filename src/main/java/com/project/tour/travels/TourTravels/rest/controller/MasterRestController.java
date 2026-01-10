package com.project.tour.travels.TourTravels.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tour.travels.TourTravels.dto.DropDownDetailVo;
import com.project.tour.travels.TourTravels.dto.DropDownWrapper;
import com.project.tour.travels.TourTravels.dto.MasterDetailVo;
import com.project.tour.travels.TourTravels.service.MasterServiceImpl;

@RestController
@RequestMapping("/api/master")
public class MasterRestController {

	@Autowired
	private MasterServiceImpl serviceImpl;

	@GetMapping("/details")
	public DropDownWrapper getDetails(@RequestBody MasterDetailVo loginRequestDto) {
		return serviceImpl.getDetails(loginRequestDto);
	}

	@GetMapping("/services-details")
	public DropDownWrapper getServicesDetails(@RequestBody MasterDetailVo loginRequestDto) {
		return serviceImpl.getServicesDetails(loginRequestDto);
	}

	@GetMapping("/get-detail-typeCode")
	public DropDownDetailVo getDetailByTypeCode(@RequestBody MasterDetailVo loginRequestDto) {
		return serviceImpl.getDetailByTypeCode(loginRequestDto);
	}

}
