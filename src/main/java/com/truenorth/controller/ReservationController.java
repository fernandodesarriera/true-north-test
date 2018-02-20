package com.truenorth.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.truenorth.repositories.ReservationRepository;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReservationRepository repo;

	//This is a kind of Override the findAll(), now by default GET up to MONTH
	@RequestMapping(path=("/default"), method = RequestMethod.GET)
	public ResponseEntity<?> findDefaultMonth() throws ParseException, JsonProcessingException {
		logger.info("Ejecutando /get by default 1 month ");
		return new ResponseEntity<Object>(repo.findDistinctByDateStartBetweenOrderByDateStartDesc(LocalDate.now(),
				LocalDate.now().plus(1, ChronoUnit.MONTHS)), HttpStatus.OK);
	}

}
