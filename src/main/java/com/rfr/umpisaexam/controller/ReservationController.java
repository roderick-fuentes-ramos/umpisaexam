package com.rfr.umpisaexam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfr.umpisaexam.dto.ReservationDto;
import com.rfr.umpisaexam.dto.ResponseDto;
import com.rfr.umpisaexam.service.ReservationService;

import lombok.RequiredArgsConstructor;

/**
 * This is the controller of the reservation program
 * This have the endpoints that can be access to 
 * manage the reservation
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReservationController {

    /**
     * Service that manages the database transactions
     */
	private final ReservationService reservationService;
    
    /**
     * Save the reservation data
     *
     * @param reservationDto           This is the reservation data that will be processed
     * 
     */
    @PostMapping("/reservation")
    public ResponseEntity<ResponseDto> createReservation(@RequestBody ReservationDto reservationDto){
    	
        return ResponseEntity.ok(reservationService.createReservation(reservationDto));
    }
    
    /**
     * View the reservation by name
     *
     * @param name           The name of the customer
     * 
     */
    @GetMapping("/reservation/{name}")
    public ResponseEntity<ResponseDto> getReservationByName(@PathVariable String name){
        return ResponseEntity.ok(reservationService.viewReservation(name));
    }    
    
    /**
     * Update the reservation information
     *
     * @param reservationId           Id of the reservation selected
     * @param reservationDto		  New reservation information for the selected reservation
     * 
     */
    @PutMapping("/reservation/{reservationId}")
    public ResponseEntity<ResponseDto> updateReservationById(@PathVariable Long reservationId,
    		@RequestBody ReservationDto reservationDto){
        return ResponseEntity.ok(reservationService.updateReservation(reservationId,reservationDto));
    }     
    
    /**
     * Cancel the reservation information
     *
     * @param reservationId           Id of the reservation selected to be cancelled
     * 
     */
    @PutMapping("/reservation/cancel/{reservationId}")
    public ResponseEntity<ResponseDto> cancelReservationById(@PathVariable Long reservationId){
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }    
    
    /**
     * Display all the reservations
     */
    @GetMapping("/reservation")
    public ResponseEntity<ResponseDto> viewAllReservations(){
    	
        return ResponseEntity.ok(reservationService.viewAllReservations());
    } 
}
