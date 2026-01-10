package com.project.tour.travels.TourTravels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tour.travels.TourTravels.dto.DropDownDetailVo;
import com.project.tour.travels.TourTravels.dto.DropDownWrapper;
import com.project.tour.travels.TourTravels.dto.MasterDetailVo;
import com.project.tour.travels.TourTravels.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MasterServiceImpl {
	private @Autowired UserMapper userMapper;

	public DropDownWrapper getDetails(MasterDetailVo vo) {
		DropDownWrapper dropDownWrapper = new DropDownWrapper();
		List<DropDownDetailVo> details = userMapper.getConfigurationValuesByTypeCode(vo.getCode());
		dropDownWrapper.setDetails(details);
		return dropDownWrapper;
	}

	public DropDownWrapper getServicesDetails(MasterDetailVo vo) {
		DropDownWrapper dropDownWrapper = new DropDownWrapper();
		List<DropDownDetailVo> details = userMapper.getServicesByCode(vo.getCode());
		dropDownWrapper.setDetails(details);
		return dropDownWrapper;
	}

	public DropDownDetailVo getDetailByTypeCode(MasterDetailVo vo) {
		DropDownDetailVo detail = userMapper.getConfigurationTypeByTypeCode(vo.getCode());
		return detail;
	}

}
