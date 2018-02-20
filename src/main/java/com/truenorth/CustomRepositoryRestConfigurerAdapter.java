package com.truenorth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.truenorth.validator.ReservationValidator;

@Configuration 
public class CustomRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", new ReservationValidator());
        validatingListener.addValidator("beforeSave", new ReservationValidator());
    }
}