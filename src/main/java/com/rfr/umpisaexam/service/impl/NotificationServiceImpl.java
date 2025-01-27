package com.rfr.umpisaexam.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.rfr.umpisaexam.dto.ResponseDto;
import com.rfr.umpisaexam.entity.Reservation;
import com.rfr.umpisaexam.repository.ReservationRepo;
import com.rfr.umpisaexam.service.NotificationService;
import com.rfr.umpisaexam.service.ReservationService;

import lombok.RequiredArgsConstructor;

/**
 * This is the implementation of the Notification Service
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

	/**
	 * Repository of the reservation table
	 */
	private final ReservationRepo reservationRepo;
	/**
	 * Service of the reservation methods
	 */
	private final ReservationService reservationService;
	
	/**
	 * 
	 * This is the implementation of the notification that reminds the customer regarding the reservation
	 * 
	 */
	@Override
	public ResponseDto notifyReservationReminder() {		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		
		try {
			reservationList = reservationService.getAllReservations();
	
			TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        formatter.setTimeZone(timeZone);
	        Calendar calendar = Calendar.getInstance();
			
			for (Reservation reservation : reservationList) {
				boolean notified = reservation.isNotified();
				if (!notified && reservation.getStatus() != "CANCELLED") {
				
					Date reservationDate = reservation.getReservationDateTime();
					String reservationDateStr = formatter.format(reservationDate);
			        calendar.setTimeZone(timeZone);
			        calendar.setTime(reservationDate);
			        calendar.add(Calendar.HOUR, -4);
			        Date notificationDate = calendar.getTime();
			        
			        Date now = new Date();		        
			        calendar.setTime(now);
			        Date convertedNow = calendar.getTime();
	
			        if (convertedNow.getTime() >= notificationDate.getTime()) {
						switch (reservation.getMethodOfCommunication()) {
							case "EMAIL" : 
								System.out.println("********NOTIFICATION: " + reservation.getName() + ", YOU ARE RESERVED FOR " + reservationDateStr + " SENT TO EMAIL********");
							break;
							case "SMS": 
								System.out.println("********NOTIFICATION: " + reservation.getName() + ", YOU ARE RESERVED FOR " + reservationDateStr + " SENT TO SMS********");
							break;
						}
						
						reservation.setNotified(true);
						reservationRepo.save(reservation);
			        }
				}
		        
			}
			
			return null;
		} catch (NoSuchElementException e) {
			return ResponseDto.builder()
	                .transactionStatusCode(500)
	                .transactionStatusDescription(e.getMessage())                
	                .build(); 
		}
		        
	}

}
