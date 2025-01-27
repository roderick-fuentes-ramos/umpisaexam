package com.rfr.umpisaexam.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity of the reservations table
 */
@Data
@Entity
@Table(name = "reservations")
public class Reservation {

	/**
	 * Primary key of the table
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Reservation date and time
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
	private Date reservationDateTime;
	
	/**
	 * Number of guests
	 */
	private Long numberOfGuests;
	/**
	 * Status of the reservation
	 */
	private String status;
	/**
	 * This tells if the customer was notified already regarding the reservation
	 */
	private boolean notified;
	
	/**
	 * Name of the customer
	 */
	private String name;
	/**
	 * Phone number of the customer
	 */
	private String phoneNumber;
	/**
	 * Email of the customer
	 */
	private String email;
	/**
	 * Selected method of cummunication to the customer
	 */
	private String methodOfCommunication;
    
}
