package com.project.tour.travels.TourTravels.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tour.travels.TourTravels.domain.UserAccessRight;
import com.project.tour.travels.TourTravels.dto.CommonDetailVo;
import com.project.tour.travels.TourTravels.mapper.UserMapper;
import com.project.tour.travels.TourTravels.repo.ConfigurationValueRepository;
import com.project.tour.travels.TourTravels.repo.UserAccessRightRepository;
import com.project.tour.travels.TourTravels.repo.UserLoginProfileRepository;

@Service("myDBUserDetailsService")
public class MyDBUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(MyDBUserDetailsService.class);

	private @Autowired UserMapper userMapper;

	@Autowired
	private UserLoginProfileRepository userLoginProfileRepository;

	@Autowired
	private UserAccessRightRepository accessRightRepository;
	@Autowired
	private ConfigurationValueRepository configurationValueRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
		MyUser myUser = null;
		try {
			String channelId = "WEB";

			CommonDetailVo profileVO = userMapper.getUserDetails(userLogin);
			if (profileVO != null) {
				logger.info("Found DB Record for userLogin : {}", userLogin);
				List<GrantedAuthority> grantedAuthorities = getPrivileges(profileVO.getId()).stream()
						.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
				myUser = new MyUser(userLogin, profileVO.getAttributeValue5(), true, true, true, true,
						grantedAuthorities);
				myUser.setFullname(profileVO.getAttributeValue6());
				myUser.setId(profileVO.getId());
				myUser.setTrackingId(channelId.charAt(0) + "- " + UUID.randomUUID().toString());
				return myUser;
			} else {
				throw new UsernameNotFoundException("No user found with username: " + userLogin);
			}
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	// UTIL

//	public final Collection<? extends SimpleGrantedAuthority> getAuthorities(final Collection<RoleVO> userRoles) {
//		return getGrantedAuthorities(getPrivileges(userRoles));
//	}

//	private final List<String> getPrivileges(final Collection<RoleVO> userRoles) {
//		final List<String> privileges = new ArrayList<String>();
//		for (final RoleVO role : userRoles) {
//			privileges.add(role.getCode());
//		}
//		return privileges;
//	}
	private final List<String> getPrivileges(Long id) {
		final List<String> privileges = new ArrayList<String>();
		List<UserAccessRight> services = accessRightRepository.getUserAccessRightByUserLoginProfileId(id,
				configurationValueRepository.findByConfigurationTypeCodeAndCode("COMMON_STATUS", "ACTIVE").getId());
		for (UserAccessRight service : services) {
			privileges.add(service.getUserService().getCode());
		}
		return privileges;
	}

	private final List<SimpleGrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
