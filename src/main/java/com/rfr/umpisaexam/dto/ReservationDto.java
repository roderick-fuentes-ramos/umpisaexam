package com.rfr.umpisaexam.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the reservation data for input
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

	/**
	 * identifier of the reservation data
	 */
	private Long id;
	
	/**
	 * Reservation data and time
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Manila")
	private Date reservationDateTime;
	
	/**
	 * Number of guests
	 */
	private Long numberOfGuests;
	
	/**
	 * status of the reservation
	 */
	private String status;

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
	 * Method of communication to be used for communication to the customer
	 */
	private String methodOfCommunication;
}
