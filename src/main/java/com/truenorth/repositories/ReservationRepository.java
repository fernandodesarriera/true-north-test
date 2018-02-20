package com.truenorth.repositories;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.truenorth.model.Reservation;

@RepositoryRestResource(collectionResourceRel = "reservation", path = "reservation")
public interface ReservationRepository extends CrudRepository<Reservation, Long>{
	
    List<Reservation> findDistinctByDateStartBetweenOrderByDateStartDesc(@Param("dateStart") 
    																	 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateStart,
    																	 @Param("dateEnd") 
    																	 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd);	
}
