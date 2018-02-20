package com.truenorth.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reservation {
	
    @Version
    @NotNull
    private Long version;
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@RestResource(exported = false)
	private long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateStart;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;
	
//	@Email(message="Please provide a valid email address")(dont know how to catch and show in the response)
	private String email;
	
	private String fullName;
	
}

