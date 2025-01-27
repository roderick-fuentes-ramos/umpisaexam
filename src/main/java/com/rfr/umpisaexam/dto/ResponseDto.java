package com.rfr.umpisaexam.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rfr.umpisaexam.entity.Reservation;

import lombok.Builder;
import lombok.Data;

/**
 * This is the object of the response of the program
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

	/**
	 * Status of the transaction
	 */
    private int transactionStatusCode;
    /**
     * Description of the status
     */
    private String transactionStatusDescription;
    /**
     * Id of the reservation
     */
    private Long reservationId;
    /**
     * Reservation data
     */
    private Reservation reservation;
    /**
     * List of the reservations
     */
    private List<Reservation> reservationList;
    /**
     * Name of the customer
     */
    private String name;
    
}
