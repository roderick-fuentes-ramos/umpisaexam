package com.rfr.umpisaexam.service;

import java.util.List;

import com.rfr.umpisaexam.dto.ReservationDto;
import com.rfr.umpisaexam.dto.ResponseDto;
import com.rfr.umpisaexam.entity.Reservation;

/**
 * Reservation service for the methods that can be executed for the reservation
 */
public interface ReservationService {
	
    /**
     * Save the reservation
     *
     * @param reservationDto           The reservation information
     * 
     */
	ResponseDto createReservation(ReservationDto reservationDto);
	/**
     * Cancel the reservation
     *
     * @param reservationId           Id of the reservation to be cancelled
     * 
     */
	ResponseDto cancelReservation(Long reservationId);
	/**
     * View the reservation thru the name of the customer
     *
     * @param name           Name of the customer
     * 
     */	
	ResponseDto viewReservation(String name);
	/**
     * Update the reservation information
     *
     * @param reservationId           Id of the reservation to be updated
     * @param reservationDto		  Reservation information
     * 
     */	
	ResponseDto updateReservation(Long reservationId, ReservationDto reservationDto);
	/**
     * Get all reservations from the database
     */		
	List<Reservation> getAllReservations();
	/**
     * Display all reservations
     */			
	ResponseDto viewAllReservations();

}
