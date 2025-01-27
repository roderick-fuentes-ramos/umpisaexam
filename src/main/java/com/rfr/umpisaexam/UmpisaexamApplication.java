package com.rfr.umpisaexam;
/**
 * Utility to manage reservation for a restaurant
 *
 * @author Roderick Fuentes Ramos
 * @version 1.0
 * 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Springboot main class
 */
@SpringBootApplication
@EnableScheduling
public class UmpisaexamApplication {

    /**
     * This starts the spring application
     */
	public static void main(String[] args) {
		SpringApplication.run(UmpisaexamApplication.class, args);
	}

}
