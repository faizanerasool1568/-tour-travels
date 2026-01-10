package com.project.tour.travels.TourTravels.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.tour.travels.TourTravels.dto.CommonDetailVo;
import com.project.tour.travels.TourTravels.dto.DropDownDetailVo;

@Mapper
public interface UserMapper {

	CommonDetailVo getUserDetails(@Param("userId") String userId);

	List<DropDownDetailVo> getConfigurationValuesByTypeCode(@Param("code") String code);

	List<DropDownDetailVo> getServicesByCode(@Param("code") String code);

	DropDownDetailVo getConfigurationTypeByTypeCode(@Param("code") String code);

}
