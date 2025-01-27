package com.rfr.umpisaexam;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfr.umpisaexam.dto.ReservationDto;
import com.rfr.umpisaexam.repository.ReservationRepo;
import com.rfr.umpisaexam.service.ReservationService;

import lombok.RequiredArgsConstructor;

/**
 * Testing for the reservation APIs
 */	
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@Execution(ExecutionMode.SAME_THREAD)
@TestMethodOrder(OrderAnnotation.class)
class ReservationControllerTests  {
	
	/**
     * MockMvc for the mockito
     */	
	@Autowired
    private MockMvc mockMvc;

	/**
     * Service for the reservation
     */	
    @Autowired
    private ReservationService reservationService;

	/**
     * Repository of the reservation table
     */	
    @Autowired
    private ReservationRepo reservationRepo;

	/**
     * Test for the create reservation api
     */	    
	@Test
	@Order(1)
	public void testCreateReservation_Success() throws Exception {
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reservationDateStr = "2025-01-25 06:48:00";
        Date reservationDate = formatter.parse(reservationDateStr);
        
        calendar.setTimeZone(timeZone);
        calendar.setTime(reservationDate);
        Date reservationDateInput = calendar.getTime();
        
	    // Mock reservation data to send
	    ReservationDto reservationDto = new ReservationDto();
	    reservationDto.setName("Merlene Ramos");
	    reservationDto.setPhoneNumber("09566118545");
	    reservationDto.setEmail("ramos_merlene@yahoo.com");
	    reservationDto.setMethodOfCommunication("SMS");
	    reservationDto.setReservationDateTime(reservationDateInput);
	    reservationDto.setNumberOfGuests(9l);
	    

	    // Perform POST request
        mockMvc.perform(post("/api/v1/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reservationDto)))
		        .andExpect(jsonPath("$.reservationId").value(1L)) 		        
		        .andExpect(jsonPath("$.transactionStatusCode").value(201)) 
		        .andExpect(jsonPath("$.transactionStatusDescription").value("Reservation created"))
		        .andExpect(jsonPath("$.reservation.name").value("Merlene Ramos"))
		        .andExpect(jsonPath("$.reservation.phoneNumber").value("09566118545"))
		        .andExpect(jsonPath("$.reservation.email").value("ramos_merlene@yahoo.com"))
		        .andExpect(jsonPath("$.reservation.methodOfCommunication").value("SMS"))
		        .andExpect(jsonPath("$.reservation.reservationDateTime").value(reservationDateStr))
		        .andExpect(jsonPath("$.reservation.numberOfGuests").value(9l));
        
	}
	
	/**
     * Test for the update reservation api
     */	
	@Test
	@Order(2)
	public void testUpdateReservation_Success() throws Exception {	
		System.out.println("reservationRepo.count()" + reservationRepo.count());
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reservationDateStr = "2025-01-25 06:48:00";
        Date reservationDate = formatter.parse(reservationDateStr);
        
        calendar.setTimeZone(timeZone);
        calendar.setTime(reservationDate);
        Date reservationDateInput = calendar.getTime();
        
	    // Mock reservation data to send
	    ReservationDto reservationDto = new ReservationDto();
	    reservationDto.setName("Merlene Ramos");
	    reservationDto.setPhoneNumber("09566118549");
	    reservationDto.setEmail("ramos_merlene@yahoo.com");
	    reservationDto.setMethodOfCommunication("EMAIL");
	    reservationDto.setReservationDateTime(reservationDateInput);
	    reservationDto.setNumberOfGuests(9l);

	    // Perform PUT request
        mockMvc.perform(put("/api/v1/reservation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reservationDto)))
		        .andExpect(jsonPath("$.transactionStatusCode").value(201)) 
		        .andExpect(jsonPath("$.transactionStatusDescription").value("Reservation updated"))
		        .andExpect(jsonPath("$.reservation.name").value("Merlene Ramos"))
		        .andExpect(jsonPath("$.reservation.phoneNumber").value("09566118549"))
		        .andExpect(jsonPath("$.reservation.email").value("ramos_merlene@yahoo.com"))
		        .andExpect(jsonPath("$.reservation.methodOfCommunication").value("EMAIL"))
		        .andExpect(jsonPath("$.reservation.reservationDateTime").value(reservationDateStr))
		        .andExpect(jsonPath("$.reservation.numberOfGuests").value(9l));

        
	}
	
	/**
     * Test for the cancel reservation api
     */	
	@Test
	@Order(3)
	public void testCancelReservation_Success() throws Exception {
	    // Perform PUT request
        mockMvc.perform(put("/api/v1/reservation/cancel/1")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(jsonPath("$.reservation.status").value("CANCELLED"))
		        .andExpect(jsonPath("$.transactionStatusCode").value(201)) 
		        .andExpect(jsonPath("$.transactionStatusDescription").value("Reservation Cancelled"));

        
	}	
	
	/**
     * Test for the view reservation by name api
     */	
	@Test
	@Order(4)
	public void testViewMyReservation_Success() throws Exception {
		String reservationDateStr = "2025-01-25 06:48:00";
	    // Perform PUT request
        mockMvc.perform(get("http://localhost:8080/api/v1/reservation")
        		.param("name", "Merlene Ramos")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(jsonPath("$.reservationList[0].name").value("Merlene Ramos"))
		        .andExpect(jsonPath("$.transactionStatusCode").value(302)) 
		        .andExpect(jsonPath("$.transactionStatusDescription").value("Reservation found"))
		        .andExpect(jsonPath("$.reservationList[0].name").value("Merlene Ramos"))
		        .andExpect(jsonPath("$.reservationList[0].phoneNumber").value("09566118549"))
		        .andExpect(jsonPath("$.reservationList[0].email").value("ramos_merlene@yahoo.com"))
		        .andExpect(jsonPath("$.reservationList[0].methodOfCommunication").value("EMAIL"))
		        .andExpect(jsonPath("$.reservationList[0].reservationDateTime").value(reservationDateStr))
		        .andExpect(jsonPath("$.reservationList[0].numberOfGuests").value(9l));

        
	}	

}
