package com.project.tour.travels.TourTravels.config;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String fullname;
	private LocalDateTime lastSuccessLogin;
	private LocalDateTime lastFailureLogin;
	private String jwtToken;
//	private List<UserFAPVO> accessRights;
	private String roles;
	private String trackingId;
	private String sessionId;

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public MyUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public MyUser(Long id, String username, String fullname, LocalDateTime lastSuccessLogin,
			LocalDateTime lastFailureLogin, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.lastSuccessLogin = lastSuccessLogin;
		this.lastFailureLogin = lastFailureLogin;
		this.fullname = fullname;
		this.id = id;
	}

	public MyUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
	}

	public Boolean isSuperUser() {
		return (this.roles != null && this.roles.contains("SUPER_ADMIN"));
	}

	public Boolean isAdmin() {
		return (this.roles != null && this.roles.contains("ADMIN"));
	}

	// to be checked
	public Boolean isCustomer() {
		return (this.roles != null && this.roles.contains("USER"));
	}

	public LocalDateTime getLastSuccessLogin() {
		return lastSuccessLogin;
	}

	public void setLastSuccessLogin(LocalDateTime lastSuccessLogin) {
		this.lastSuccessLogin = lastSuccessLogin;
	}

	public LocalDateTime getLastFailureLogin() {
		return lastFailureLogin;
	}

	public void setLastFailureLogin(LocalDateTime lastFailureLogin) {
		this.lastFailureLogin = lastFailureLogin;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return this.id + "-" + this.getUsername();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof MyUser) {
			return this.id.equals(((MyUser) rhs).getId());
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	public Boolean isUsingMobile() {
		return !this.trackingId.startsWith("W-");
	}

	public Boolean isUsingWeb() {
		return this.trackingId.startsWith("W-");
	}

}