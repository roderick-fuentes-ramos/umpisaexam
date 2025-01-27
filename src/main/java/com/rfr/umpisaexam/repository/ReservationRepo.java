package com.rfr.umpisaexam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfr.umpisaexam.entity.Reservation;

/**
 * This is the repository that have the methods that can be executed against the database
 */
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
	
	/**
	 * This search the reservation by name
	 */
	List<Reservation> findByName(String name);

}
