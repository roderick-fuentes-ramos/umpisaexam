package com.rfr.umpisaexam.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rfr.umpisaexam.service.NotificationService;

/**
 * Utility for scheduling the notification process
 */	
@Component
public class Scheduler {

	/**
     * Notification service
     */	
    @Autowired
    private NotificationService notificationService; 

	/**
     * Notify the customer regarding the reservation if the reservation is happening 
     * after 4 hours from now and this is being done every 5 minutes
     */	
    @Scheduled(fixedRate = 300000) // this is for 5 minutes(300000) : ratio is fixedRate is 1000 for 1 second
    public void scheduleTask() {
    	notificationService.notifyReservationReminder();
    }

}

