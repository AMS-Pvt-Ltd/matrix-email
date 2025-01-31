package com.matrix.email.cronjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProjectSurveyLinkGenerationCronjob {

	
	@Autowired
    private RestTemplate restTemplate;
	
	@Scheduled(cron = "30 * * * * *")  // This runs every minute at the 30th second
	public void sendPreparerEmailWithExternalApi() {
	    try {
	        // Define the Base URL for the send-email endpoint with the path variable
	        String baseURL = "http://182.184.52.88:8122/api/projects/survey/email";
	        
	        // Construct the full URL with the baseURL path variable
	        String fullURL = baseURL + "/preparer/send-email";
	        
	        // Make the GET request to the controller endpoint
	        ResponseEntity<Boolean> response = restTemplate.getForEntity(fullURL, Boolean.class);

	        // Handle the response from the external API
	        System.out.println("Response from send-email endpoint: " + response.getBody());

	        // If the response is successful, log success, else log failure
	        if (response.getBody() != null && response.getBody()) {
	            System.out.println("Email sent successfully.");
	        } else {
	            System.out.println("Failed to send email.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception for debugging
	    }
	}

	@Scheduled(cron = "30 * * * * *")  // This runs every minute at the 30th second
	public void sendReviwerEmailWithExternalApi() {
	    try {
	        // Define the Base URL for the send-email endpoint with the path variable
	        String baseURL = "http://182.184.52.88:8122/api/projects/survey/email";
	        
	        // Construct the full URL with the baseURL path variable
	        String fullURL = baseURL + "/reviewer/send-email";
	        
	        // Make the GET request to the controller endpoint
	        ResponseEntity<Boolean> response = restTemplate.getForEntity(fullURL, Boolean.class);

	        // Handle the response from the external API
	        System.out.println("Response from send-email endpoint: " + response.getBody());

	        // If the response is successful, log success, else log failure
	        if (response.getBody() != null && response.getBody()) {
	            System.out.println("Email sent successfully.");
	        } else {
	            System.out.println("Failed to send email.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception for debugging
	    }
	}
	
}
