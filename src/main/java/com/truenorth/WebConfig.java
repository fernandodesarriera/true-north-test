package com.truenorth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class WebConfig {
	
    @Bean
    public FilterRegistrationBean filterRegistration(){
    	final FilterRegistrationBean registration = new FilterRegistrationBean();
    	registration.setFilter(shallowEtagHeaderFilter());
    	registration.addUrlPatterns("/api/*");
    	registration.setName("etagFilter");
    	registration.setOrder(1);
    	return registration;
    	
    }
    
    @Bean("etagFilter")
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }   
}