package com.matrix.email.cronjob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@Component
public class EmailCronjob {

	@Autowired
    private RestTemplate restTemplate;
	  // Define the external API URL

    // Run the method every 5 minutes (you can change the cron expression as needed)
	@Scheduled(cron = "30 * * * * *")  
    public ResponseEntity<Boolean> sendEmailWithExternalApi() {
        try {
            // Define the external API URL
            String externalApiUrl = "http://Localhost:8080/api/control-testing-phase/email";
            
            // Make a GET call to the external API
            String response = restTemplate.getForObject(externalApiUrl, String.class);
            
            
            	externalApiUrl = "http://Localhost:8080/api/operational-control-testing-phase/email";
            	
            	
            	response = restTemplate.getForObject(externalApiUrl, String.class);
            	
            	
            // Handle the response from the external API
            System.out.println("Response from external API: " + response);

            
            
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(500).body(false);
        }
    }
}