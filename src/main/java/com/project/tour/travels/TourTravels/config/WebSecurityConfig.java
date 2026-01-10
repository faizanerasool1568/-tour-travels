//package com.project.tour.travels.TourTravels.config;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.csrf.InvalidCsrfTokenException;
//import org.springframework.security.web.session.HttpSessionEventPublisher;
//
//@Configuration
//@EnableWebSecurity
//@PropertySource("classpath:git.properties")
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//class WebSecurityConfig {
//
//	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
//
//	private @Autowired AuthenticationSuccessHandler myAuthenticationSuccessHandler;
//
//	private @Autowired LogoutSuccessHandler myLogoutSuccessHandler;
//
//	private @Autowired LogoutHandler myLogoutHandler;
//
//	private @Autowired AuthenticationFailureHandler authenticationFailureHandler;
//
//	private @Autowired MyDBUserDetailsService dbUserDetailsService;
//
//	private @Autowired WebAppProperties props;
//	private @Autowired Environment env;
//
//	public WebSecurityConfig() {
//		super();
//	}
//
////	@Bean
////	public WebSecurityCustomizer webSecurityCustomizer() {
////
//////		MyStrictHttpFirewall firewall = new MyStrictHttpFirewall(props.getSecurity().getAllowedHostNames(),
//////				!DefaultProfileUtil.isWithinClayfin(env));
//////		return (web) -> web.httpFirewall(firewall).ignoring().antMatchers("/app/**/*.{js,html}").antMatchers(
//////				"/themes/**", "/themes_ar/**", "/js/**", "/error/**", "/css/**", "/scripts/**", "/i18n/**", "/wro/**",
//////				"/images/**", "/secimg/**", "/photos/**", "/fonts/**", "/swagger-ui/index.html", "/thankyou",
//////				"/invoke/middleware/http", "/invoke/middleware/**", "/app-doc", "/banner");
////
////	}
//
//	@Bean
//	public AuthenticationManager authenticationManager() {
//		return new ProviderManager(daoAuthenticationProvider());
//		// If you have multiple authentication methods, you can use:
//		// List.of(daoAuthenticationProvider(), customAuthenticationProvider())
//	}
//
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(dbUserDetailsService);
//		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//		return authenticationProvider;
//	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http.authorizeRequests().antMatchers(HttpMethod.POST, "/getEmail/*").authenticated()
//				.antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
//				.antMatchers(HttpMethod.POST, "/user/**").hasAuthority("USER").antMatchers(HttpMethod.POST, "/guest")
//				.permitAll().anyRequest().authenticated().and().formLogin().and()
//				.authenticationManager(authenticationManager()).csrf().disable().build();
//
////		AuthenticationManagerBuilder authenticationManagerBuilder = http
////				.getSharedObject(AuthenticationManagerBuilder.class);
////		authenticationManagerBuilder.authenticationProvider(jwtAuthProvider());
////
////		String cspHost = "";
////		String contentSecurityPolicyHosts = props.getContentSecurityPolicyHosts();
////		if (StringUtils.hasLength(contentSecurityPolicyHosts)) {
////			cspHost = contentSecurityPolicyHosts;
////		}
////
////		CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
////
////		// @formatter:off
////		http.sessionManagement().sessionFixation().newSession().invalidSessionUrl("/error/session-fixation")
////				.maximumSessions(props.getSecurity().getMaximumSessions())
////				.maxSessionsPreventsLogin(props.getSecurity().getMaxSessionsPreventsLogin())
////				.expiredUrl("/error/session-terminate").and().and().exceptionHandling()
////				.accessDeniedPage("/error/access-denied").accessDeniedHandler(accessDeniedHandler).and().formLogin()
////				.loginPage("/login").usernameParameter("username").passwordParameter("password")
////				.authenticationDetailsSource(new MyExtraAuthenticationDetailsSource()).defaultSuccessUrl("/home", true)
////				.failureUrl(props.getStartURL() + "?error=true").successHandler(myAuthenticationSuccessHandler)
////				.failureHandler(authenticationFailureHandler)
////
////				.and().logout().logoutUrl("/logout").addLogoutHandler(myLogoutHandler)
////				.logoutSuccessHandler(myLogoutSuccessHandler).invalidateHttpSession(true)
////				.deleteCookies("JSESSIONID",  "submenuheader")
////
////				.and().headers()
////				.contentSecurityPolicy(
////						"default-src 'self' "+cspHost+" https: data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' data: blob:; connect-src 'self' https:; img-src 'self' https: data:; style-src 'self' 'unsafe-inline' https:; frame-ancestors 'self'")
////				.and().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000).and()
////				.frameOptions().sameOrigin().referrerPolicy(ReferrerPolicy.SAME_ORIGIN).and().and().authorizeRequests()
////				.antMatchers(HttpMethod.OPTIONS, "/**").denyAll().antMatchers(HttpMethod.TRACE, "/**").denyAll()
////				.antMatchers("/", "/keep-alive*", "/home/keep-alive", "/thankyou*", "/login*", "/login/blocked*",
////						"/txn/blocked", "/login/key*", "/login/captcha*", "/login/two-factor-auth/**", "/login/cancel",
////						"/prelogin/**", "/logout*", "/error/timed-out*", "/error/**", "/login/first-time/**",
////						"/change-password/**", "/clayfin/job/**", "/users/credentials/password-success",
////						"/users/preferences/secure-images/**", "/server/sync/**", "/login/soft-token-change-flow/**",
////						"/corporate-register/**","/login/login-otp-blocked-facl*","/login/login-common-api-error*","/login/suspend-acces-otp","/login/suspend-access-deactivate-user-id","/login/suspend-activity")
////				.permitAll().antMatchers("/thankyou").permitAll().antMatchers("/management/health").permitAll()
////				.antMatchers("/management/info").permitAll().antMatchers("/management/env").permitAll()
////				.antMatchers("/management/auditevents").permitAll().antMatchers("/management/beans").permitAll()
////				.antMatchers("/management/trace").permitAll().antMatchers("/management/dump").permitAll()
////				.antMatchers("/management/metrics").permitAll().antMatchers("/management/**").anonymous()
////				.antMatchers("/scripts/**", "/themes/**", "/themes_ar/**", "/images/**", "/photos/**","/banner/**", "/wro/**",
////						"/secimg/**", "/prelogin/app-info/**")
////				.permitAll().antMatchers("/emailError*", "/error/**").permitAll().anyRequest().authenticated().and()
////				.csrf()
////					.csrfTokenRepository(new CustomCsrfTokenRepository())
////				.ignoringAntMatchers("/prelogin/app-info/**");
////
////		// @formatter:on 
////		return http.build();
//	}
//
//	/*
//	 * @Bean public CsrfTokenRepository customCsrfRepository(){ return new
//	 * CustomCsrfTokenRepository(); }
//	 */
//
////	@Bean
////	public CaptchaGenerator captchaGenerator() {
////		return new CaptchaGenerator();
////	}
////
////	@Bean
////	public AuthenticationResultListener authenticationResultListener() {
////		return new AuthenticationResultListener();
////	}
//
//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//		return new HttpSessionEventPublisher();
//	}
//
////	@Bean
////	public AuthenticationProvider jwtAuthProvider() {
////		final MyJWTAuthenticationProvider authProvider = new MyJWTAuthenticationProvider();
////		authProvider.setUserWebService(userWebService);
////		return authProvider;
////	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}
//
//	@Bean
//	public ServletContextInitializer servletContextInitializer(@Value("${secure.cookie}") boolean secure,
//			@Value("${secure.domain}") String domain) {
//		return new ServletContextInitializer() {
//
//			@Override
//			public void onStartup(ServletContext servletContext) throws ServletException {
//				if (secure) {
//					logger.info("Secure Cookie enabled : true");
//					logger.info("The Application should run in HTTPS Protocol..if not u will see Session Invalid");
//					servletContext.getSessionCookieConfig().setSecure(secure);
//					if (domain != null && !"not-set".equals(domain)) {
//						servletContext.getSessionCookieConfig().setDomain((domain));
//					} else {
//						logger.info("Domain Cookie enabled : false or domain is not-set");
//					}
//				} else {
//					logger.info("Secure Cookie enabled : false");
//				}
//			}
//		};
//	}
//
//	private static boolean isAjax(HttpServletRequest request) {
//		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
//	}
//
//	static class CustomAccessDeniedHandler implements AccessDeniedHandler {
//		@Override
//		public void handle(HttpServletRequest request, HttpServletResponse response,
//				AccessDeniedException accessDeniedException) throws IOException, ServletException {
//			// do some stuff to change response code
//			if (accessDeniedException instanceof InvalidCsrfTokenException) {
//				logger.info("Invalid CSRF Token passed");
//				response.setHeader("X_CSRF_ERROR", "INVALID_CSRF_TOKEN");
//				if (isAjax(request)) {
//					response.setStatus(HttpServletResponse.SC_OK);
//				} else {
//					response.sendRedirect(request.getContextPath() + File.separator + "error/session-invalid");
//				}
//			}
//		}
//	}
//}
