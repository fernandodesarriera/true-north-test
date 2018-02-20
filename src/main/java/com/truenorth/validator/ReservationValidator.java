package com.truenorth.validator;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.truenorth.model.Reservation;

public class ReservationValidator implements Validator {
 
    @Override
    public boolean supports(Class<?> clazz) {
        return Reservation.class.equals(clazz);
    }
    
    @Autowired EmailValidator emailValidator;
 
    @Override
    public void validate(Object obj, Errors errors) {
    	Reservation reservation = (Reservation) obj;
    	LocalDate today = LocalDate.now(); 
    	
    	//wrong dates
    	if(reservation.getDateEnd().isBefore(reservation.getDateStart()) || reservation.getDateStart().isBefore(today)){
	    	errors.rejectValue("dateStart", "", "Check dateStart and dateEnd, they do not have coherence");
    	}
    		
		//minimum 1 day(s)
	    if (today.equals(reservation.getDateStart())){
	    	errors.rejectValue("dateStart", "", "The campsite can be reserved minimum 1 day(s) ahead of arrival");
	    }
	    
	    //up to 1 month
	    long diffMonthInAdvance = Duration.between(today.atStartOfDay(), reservation.getDateStart().atStartOfDay()).toDays();
		if(diffMonthInAdvance > 30){
			errors.rejectValue("dateStart", "", "The campsite cannot be reserved up to 1 month in advance.");
		}

	    //max 3 days
	    long diffMaxDays = Duration.between(reservation.getDateStart().atStartOfDay(), reservation.getDateEnd().atStartOfDay()).toDays();
		if(diffMaxDays > 3){
			errors.rejectValue("dateStart", "", "The campsite can be reserved for max 3 days");
		}
		
		//email
		if(StringUtils.isEmpty(reservation.getEmail())){
			errors.rejectValue("email", "email.empty", "Please provide an email address");
		} else if(!new EmailValidator().validate(reservation.getEmail())){
			errors.rejectValue("email", "email.empty", "Please provide a valid email address");
		}

		//fullName
		if(StringUtils.isEmpty(reservation.getFullName())){
			errors.rejectValue("dateStart", "name.invalid", "Please provide a fullName");
		}
    
    }

}