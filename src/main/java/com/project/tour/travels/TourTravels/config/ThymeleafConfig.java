package com.project.tour.travels.TourTravels.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@Configuration
public class ThymeleafConfig {

	 @Bean
	    public IDialect springDataDialect() {
	        return new SpringDataDialect();
	    }

}
