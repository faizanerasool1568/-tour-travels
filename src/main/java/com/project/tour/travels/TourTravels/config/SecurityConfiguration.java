package com.project.tour.travels.TourTravels.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@SuppressWarnings("deprecation")
public class SecurityConfiguration {

	// private final AuthenticationManagerBuilder authenticationManagerBuilder;

//	private final UserDetailsService userDetailsService;

	// private final TokenProvider tokenProvider;

	// private final SessionRegistry sessionRegistry;

	// private final CorsFilter corsFilter;
	// private final AppProperties props;
	// private @Autowired Environment env;
	private @Autowired AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

//	public SecurityConfiguration(AppProperties props, AuthenticationManagerBuilder authenticationManagerBuilder,
//			UserDetailsService userDetailsService, TokenProvider tokenProvider, SessionRegistry sessionRegistry,
//			CorsFilter corsFilter, FeatureManager featureManager, ILdapAuthentication ldapAuth) {
//		this.props = props;
//		this.authenticationManagerBuilder = authenticationManagerBuilder;
//		this.userDetailsService = userDetailsService;
//		this.tokenProvider = tokenProvider;
//		this.sessionRegistry = sessionRegistry;
//		this.corsFilter = corsFilter;
//		this.featureManager = featureManager;
//		this.ldapAuth = ldapAuth;
//	}

//	@Bean
//	public PooledPBEStringEncryptor encryptor() {
//		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//		encryptor.setPoolSize(4); // This would be a good value for a 4-core system
//		encryptor.setPassword("vayana-india-enc");
//		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
//		return encryptor;
//	}

	public static void main(String[] args) {
		String pass = new BCryptPasswordEncoder().encode("cust@2020");
		String pass1 = new BCryptPasswordEncoder().encode("auth@2020");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyDBUserDetailsService();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}

//	@PostConstruct
//	public void init() {
//		try {
//			logger.info("LDAP in SecurityConfig:" + featureManager.isActive(BootFeatures.ADMIN_LDAP_FLAG));
//			// this Auth Provider is for DB authentication and Authorization
//			if (!props.getSecurity().getAuthentication().getSkipPwd()) {
//				MyDBAuthenticationProvider dbAuthProvider = new MyDBAuthenticationProvider();
//				dbAuthProvider.setUserDetailsService(userDetailsService);
//				dbAuthProvider.setPasswordEncoder(passwordEncoder());
//				authenticationManagerBuilder.authenticationProvider(dbAuthProvider);
//			} else {
//				MyDummyAuthenticationProvider dbAuthProvider = new MyDummyAuthenticationProvider();
//				dbAuthProvider.setUserDetailsService(userDetailsService);
//				dbAuthProvider.setPasswordEncoder(passwordEncoder());
//				if (props.getSecurity().getAuthentication().getExcludeSkipPwdUsers() != null) {
//					dbAuthProvider.setSkipPasswordUsers(
//							Arrays.stream(props.getSecurity().getAuthentication().getExcludeSkipPwdUsers().split(","))
//									.map(Long::valueOf).collect(Collectors.toList()));
//				}
//				authenticationManagerBuilder.authenticationProvider(dbAuthProvider);
//			}
//
//			if (featureManager.isActive(BootFeatures.ADMIN_LDAP_FLAG)
//					&& DefaultProfileUtil.isProfileExits(env, "admin")) {
//				// this Auth Provider is for LDAP. Ldap for Authentication and DB for
//				// Authorization.
//				MyLdapAuthenticationProvider ldapAuthProvider = new MyLdapAuthenticationProvider(ldapTemplate(), env,
//						props.getSecurity().getAuthentication().getLdap(), ldapAuth);
//				ldapAuthProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//				ldapAuthProvider.setUserDetailsService(userDetailsService);
//				authenticationManagerBuilder.authenticationProvider(ldapAuthProvider);
//			}
//		} catch (Exception e) {
//			logger.info(e.getMessage(), e);
//			logger.info("Security Configuration error...Invalid Configuration in SecurityConfiguration class");
//		}
//	}

//	@Bean
//	public Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint() {
//		return new Http401UnauthorizedEntryPoint();
//	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/bower_components/**").antMatchers("/i18n/**")
				.antMatchers("/content/**").antMatchers("/swagger-ui/index.html").antMatchers("/test/**")
				.antMatchers("/h2-console/**").antMatchers("/api/daemon/**")
				.antMatchers("/secimg/**, /photos/**, /video/**, /downloads/**, /reports/**");

	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.headers().frameOptions().disable().and()
				.authorizeHttpRequests(request -> request.antMatchers("/prelogin/**").permitAll().antMatchers("/css/**")
						.permitAll().antMatchers("/scripts/**").permitAll().antMatchers("/images/**").permitAll()
						.antMatchers("/js/**").permitAll().antMatchers("/fonts/**").permitAll()
						.antMatchers("/prelogin/validateLogin").permitAll().antMatchers("/favicon.ico/**").permitAll()
						.anyRequest().authenticated())
				.formLogin().loginPage("/prelogin/home").usernameParameter("userLogin").passwordParameter("password")
				.loginProcessingUrl("/prelogin/validateLogin")
				// .authenticationDetailsSource(new MyExtraAuthenticationDetailsSource())
				//.defaultSuccessUrl("/login/home", true)
				.defaultSuccessUrl("/login/home", true)
				.successHandler(myAuthenticationSuccessHandler)
				//.failureHandler(authenticationFailureHandler)

				//.and().logout().logoutUrl("/logout").addLogoutHandler(myLogoutHandler)
				//.logoutSuccessHandler(myLogoutSuccessHandler).invalidateHttpSession(true)
				
				.and().csrf().disable();
		// .csrfTokenRepository(new CustomCsrfTokenRepository());
		// .logout(LogoutConfigurer::permitAll);
		// .formLogin(
		// form -> form.loginPage("/prelogin/test").defaultSuccessUrl("/login/home1",
		// true).permitAll())

		return http.build();
	}
//				.formLogin(form -> form.loginPage("/prelogin/home").defaultSuccessUrl("/prelogin/home1", true).permitAll())
	// .loginPage("/login").usernameParameter("username").passwordParameter("password")
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		/*
//		 * http.sessionManagement().maximumSessions(1) // maximum number of concurrent
//		 * sessions for one user .sessionRegistry(sessionRegistry).and().and()
//		 */
////		http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
////				.authenticationEntryPoint(http401UnauthorizedEntryPoint()).and().csrf().disable().headers()
////				.frameOptions().disable().and().sessionManagement()
////				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////				  .sessionFixation().migrateSession()
////				  .maximumSessions(1)
////				  .sessionRegistry(sessionRegistry).and()
//		return http.authorizeRequests().antMatchers(HttpMethod.POST, "/getEmail/*").authenticated()
//				.antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
//				.antMatchers(HttpMethod.POST, "/user/**").hasAuthority("USER").antMatchers(HttpMethod.POST, "/guest")
//				.permitAll().anyRequest().authenticated().and().formLogin().and().csrf().disable().build();
//		// return http.build();
//	}

//	private JWTConfigurer securityConfigurerAdapter() {
//		return new JWTConfigurer(tokenProvider);
//	}

}
