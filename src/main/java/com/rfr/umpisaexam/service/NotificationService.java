package com.rfr.umpisaexam.service;

import org.springframework.stereotype.Service;

import com.rfr.umpisaexam.dto.ResponseDto;

/**
 * This is the service for the reminder to the customer
 */
@Service
public interface NotificationService {
	
	/**
	 * Notify the customer with regards to the reservation
	 */
	ResponseDto notifyReservationReminder();

}
