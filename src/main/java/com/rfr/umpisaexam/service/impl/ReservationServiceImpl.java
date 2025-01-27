package com.rfr.umpisaexam.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rfr.umpisaexam.dto.ReservationDto;
import com.rfr.umpisaexam.dto.ResponseDto;
import com.rfr.umpisaexam.entity.Reservation;
import com.rfr.umpisaexam.repository.ReservationRepo;
import com.rfr.umpisaexam.service.ReservationService;

import lombok.RequiredArgsConstructor;

/**
 * This is the implementation of the reservation service 
 */
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
	
	/**
	 * Repository of the reservation table 
	 */
	private final ReservationRepo reservationRepo;

    /**
     * Implementation for saving the reservation
     *
     * @param reservationDto           The reservation information
     * 
     */
	@Override
	public ResponseDto createReservation(ReservationDto reservationDto) {
		
		// TODO Auto-generated method stub
		Reservation reservation = new Reservation();
		
        if (reservationDto.getName() != null) reservation.setName(reservationDto.getName());;
        if (reservationDto.getPhoneNumber() != null) reservation.setPhoneNumber(reservationDto.getPhoneNumber());;
        if (reservationDto.getMethodOfCommunication() != null) reservation.setMethodOfCommunication(reservationDto.getMethodOfCommunication());
        if (reservationDto.getEmail() != null) reservation.setEmail(reservationDto.getEmail());
        if (reservationDto.getReservationDateTime() != null) reservation.setReservationDateTime(reservationDto.getReservationDateTime());
        if (reservationDto.getNumberOfGuests() != null) reservation.setNumberOfGuests(reservationDto.getNumberOfGuests());
        reservation.setStatus("CONFIRMED");

		switch (reservation.getMethodOfCommunication()) {
			case "EMAIL" : System.out.println("********STATUS NOTIFICATION: CONFIRMED RESERVATION SENT TO EMAIL********");
			break;
			case "SMS": System.out.println("********STATUS NOTIFICATION: CONFIRMED RESERVATION SENT TO SMS********");
			break;
		}

        Reservation savedReservation = reservationRepo.save(reservation);        
		Long reservationId = savedReservation.getId();
		
        String message = ("Reservation created");
        return ResponseDto.builder()
        		.reservationId(reservationId)
        		.reservation(savedReservation)
                .transactionStatusCode(201)
                .transactionStatusDescription(message)                
                .build();
	
	}
	
	/**
     * Implementation for canceling the reservation
     *
     * @param reservationId           Id of the reservation to be cancelled
     * 
     */
	@Override
	public ResponseDto cancelReservation(Long reservationId) {
		// TODO Auto-generated method stub
		Reservation reservation = new Reservation();
		Reservation savedReservation = null;

		try {
			// TODO Auto-generated method stub
	    	Optional<Reservation> reservationOptional = reservationRepo.findById(reservationId);
	    	
	    	if (reservationOptional.isPresent()) {
	    	    reservation = reservationOptional.get();
	    	    
	            reservation.setStatus("CANCELLED");
	            
	            savedReservation = reservationRepo.save(reservation);
	            
				if (reservationId != null) {
		        	
					switch (reservation.getMethodOfCommunication()) {
						case "EMAIL" : System.out.println("********STATUS NOTIFICATION: CANCELLED RESERVATION SENT TO EMAIL********");
						break;
						case "SMS": System.out.println("********STATUS NOTIFICATION: CANCELLED RESERVATION SENT TO SMS********");
						break;
					}
				}

	    	} else {
	    	    throw new NoSuchElementException("Reservation not found"); 
	    	}
		    return ResponseDto.builder()
		    		.reservation(savedReservation)
	                .transactionStatusCode(201)
	                .transactionStatusDescription("Reservation Cancelled")                
	                .build();
        } catch (NoSuchElementException e) {
        	return ResponseDto.builder()
        			.reservationId(reservationId)
	                .transactionStatusCode(401)
	                .transactionStatusDescription(e.getMessage())                
	                .build();       	
        }	
	}


	/**
     * Implementation for updating the reservation information
     *
     * @param reservationId           Id of the reservation to be updated
     * @param reservationDto		  Reservation information
     * 
     */	
	@Override
	public ResponseDto updateReservation(Long reservationId, ReservationDto reservationDto) {
		// TODO Auto-generated method stub
		Reservation reservation = new Reservation();
		Reservation savedReservation = null;

		try {
			// TODO Auto-generated method stub
	    	Optional<Reservation> reservationOptional = reservationRepo.findById(reservationId);
	    	
	    	if (reservationOptional.isPresent()) {
	    	    reservation = reservationOptional.get();
	    	    
	            if (reservationDto.getName() != null) reservation.setName(reservationDto.getName());;
	            if (reservationDto.getPhoneNumber() != null) reservation.setPhoneNumber(reservationDto.getPhoneNumber());;
	            if (reservationDto.getMethodOfCommunication() != null) reservation.setMethodOfCommunication(reservationDto.getMethodOfCommunication());
	            if (reservationDto.getEmail() != null) reservation.setEmail(reservationDto.getEmail());
	            if (reservationDto.getReservationDateTime() != null) reservation.setReservationDateTime(reservationDto.getReservationDateTime());
	            if (reservationDto.getNumberOfGuests() != null) reservation.setNumberOfGuests(reservationDto.getNumberOfGuests());
	            reservation.setStatus("CONFIRMED");
	            
	            savedReservation = reservationRepo.save(reservation);        
	            
				switch (reservation.getMethodOfCommunication()) {
					case "EMAIL" : System.out.println("********STATUS NOTIFICATION: CONFIRMED RESERVATION SENT TO EMAIL********");
					break;
					case "SMS": System.out.println("********STATUS NOTIFICATION: CONFIRMED RESERVATION SENT TO SMS********");
					break;
				}

	    	} else {
	    	    throw new NoSuchElementException("Reservation not found"); 
	    	}
		    return ResponseDto.builder()
		    		.reservation(savedReservation)
	                .transactionStatusCode(201)
	                .transactionStatusDescription("Reservation updated")                
	                .build();
        } catch (NoSuchElementException e) {
        	return ResponseDto.builder()
        			.reservationId(reservationDto.getId())
	                .transactionStatusCode(401)
	                .transactionStatusDescription(e.getMessage())                
	                .build();       	
        }		
		

	}

	/**
     * Implementation for viewing the reservation thru the name of the customer
     *
     * @param name           Name of the customer
     * 
     */	
	@Override
	public ResponseDto viewReservation(String name) {
		
		try {
			// TODO Auto-generated method stub
	    	List<Reservation> reservations = reservationRepo.findByName(name);
	    	
	    	if (reservations.size() < 1) {
	    		 throw new NoSuchElementException("Reservation not found"); 
	    	}
	    	
		    return ResponseDto.builder()
		    		.reservationList(reservations)
	                .transactionStatusCode(302)
	                .transactionStatusDescription("Reservation found")                
	                .build();
        } catch (NoSuchElementException e) {
        	return ResponseDto.builder()
        			.name(name)
	                .transactionStatusCode(401)
	                .transactionStatusDescription(e.getMessage())                
	                .build();       	
        }
	}

	/**
     * Implementation for getting all reservations from the database
     */	
	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return reservationRepo.findAll();
	}

	/**
     * Implementation for displaying all reservations
     */	
	@Override
	public ResponseDto viewAllReservations() {
		// TODO Auto-generated method stub
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		try {
			// TODO Auto-generated method stub
			reservations = reservationRepo.findAll();
	    	
	    	if (reservations.size() < 1) {
	    		throw new NoSuchElementException("Reservation not found");
	    	}
		    
	    	return ResponseDto.builder()
		    		.reservationList(reservations)
	                .transactionStatusCode(302)
	                .transactionStatusDescription("Reservation found")                
	                .build();
        } catch (NoSuchElementException e) {
        	return ResponseDto.builder()
	                .transactionStatusCode(401)
	                .transactionStatusDescription(e.getMessage())                
	                .build();       	
        }
	}
}
