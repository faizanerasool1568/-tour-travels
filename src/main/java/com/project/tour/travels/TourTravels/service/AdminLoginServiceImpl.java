package com.project.tour.travels.TourTravels.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tour.travels.TourTravels.domain.ConfigurationType;
import com.project.tour.travels.TourTravels.dto.LoginRequestDto;
import com.project.tour.travels.TourTravels.repo.ConfigurationTypeRepository;
import com.project.tour.travels.TourTravels.repo.ConfigurationValueRepository;
import com.project.tour.travels.TourTravels.repo.UserLoginProfileRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminLoginServiceImpl {

	@Autowired
	private ConfigurationTypeRepository configurationTypeRepository;
	@Autowired
	private ConfigurationValueRepository configurationValueRepository;
	@Autowired
	private UserLoginProfileRepository loginProfileRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String login(LoginRequestDto loginRequestDto) {
		ConfigurationType configurationType = configurationTypeRepository.getConfigurationTypeByCode("ADMIN_PROFILE");
		if (configurationType != null)
			return configurationType.getDescription();
		else
			return null;
	}
//	 id INT NOT NULL PRIMARY KEY, 
//	   VERSION INT NOT NULL,
//	   USER_ID VARCHAR(200) NOT NULL, 
//	   USER_TYPE_ID INT NOT NULL, 
//	   CUSTOMER_PROFILE_ID INT NOT NULL,
//	   PARTNER_PROFILE_ID INT NOT NULL,
//	   PASSWORD VARCHAR(200) NOT NULL,
//	   PIN_NUMBER VARCHAR(200) NOT NULL,
//	   LAST_SUCCESS_LOGIN TIMESTAMP,
//	   LAST_FAILURE_LOGIN TIMESTAMP,
//	   STATUS_ID INT NOT NULL,
//	   CREATED_BY INT NOT NULL,
//	   CREATED_DATE TIMESTAMP,
//	   MODIFIED_BY INT NOT NULL,
//	   MODIFIED_DATE TIMESTAMP

	@PostConstruct
	public void postConstruct() {
//		UserProfileInfo userProfileInfo = new UserProfileInfo();
//		userProfileInfo.setMobileNumber("6301306459");
//		userProfileInfo.setEmailAddress("f@gmail.com");
//		userProfileInfo.setAddress("Tanda");
//		UserLoginProfile userLoginProfile = new UserLoginProfile();
//		userLoginProfile.setUserId("faizan");
//		userLoginProfile.setFullName("faizan");
//		userLoginProfile.setStatus(configurationValueRepository.getConfigurationValueByCode("ACTIVE"));
//		userLoginProfile.setUserTypeInfo(1l);
//		userLoginProfile.setUserProfileInfo(userProfileInfo);
//		userLoginProfile.setCreatedBy(1l);
//		userLoginProfile.setCreatedDate(LocalDateTime.now());
		System.out.println("test password is   " + passwordEncoder.encode("faizan123"));
		// loginProfileRepository.save(userLoginProfile);
//		 userProfileInfo = new UserProfileInfo();
//		userProfileInfo.setMobileNumber("6301306459");
//		userProfileInfo.setEmailAddress("f1@gmail.com");
//		userProfileInfo.setAddress("Tanda1");
//		 userLoginProfile = new UserLoginProfile();
//		userLoginProfile.setUserId("faizan");
//		userLoginProfile.setUserTypeInfo(userTypeInfoRepository.getUserTypeByCode("CUSTOMER"));
//		userLoginProfile.setUserProfileInfo(userProfileInfo);
//		userLoginProfile.setStatus(configurationValueRepository.getConfigurationValueByCode("ACTIVE"));
//		userLoginProfile.setCreatedBy(1l);
//		userLoginProfile.setCreatedDate(LocalDateTime.now());
//		userLoginProfile.setPassword(passwordEncoder.encode("faizan124"));
//		loginProfileRepository.save(userLoginProfile);
	}

}
