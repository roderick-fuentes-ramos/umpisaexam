package com.rfr.umpisaexam.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rfr.umpisaexam.dto.ResponseDto;

/**
 * This is the handler for the exception when there is no input to the reservation APIs
 */
@RestControllerAdvice
public class RestApiExceptionHandler {

    /**
     * Handle the HttpMessageNotReadableException exception
     *
     * @param ex           The exception
     * 
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDto handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    	return ResponseDto.builder()
    			.transactionStatusCode(401)
                .transactionStatusDescription("Missing Reservation")                
                .build(); 
    }
}