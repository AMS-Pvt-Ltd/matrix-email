package com.matrix.email.controller;



import com.matrix.email.services.EmailService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/email")
public class EmailController {


    @Autowired
    private EmailService emailService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send")
    public ResponseEntity<Boolean> sendEmail(@RequestParam(required = false) String to,
                                            @RequestParam(required = false) List<String> toList,
                                            @RequestParam String subject,
                                            @RequestParam String body,
                                            @RequestParam(required = false) String attachmentPath) {
        try {
            if (to != null && toList == null && attachmentPath == null) {
                // Simple email to one recipient
                emailService.sendEmail(to, subject, body);
            } else if (toList != null && to == null && attachmentPath == null) {
                // Email to multiple recipients
                emailService.sendEmailToMultiple(toList, subject, body);
            } else if (to != null && toList == null && attachmentPath != null) {
                // Email with attachment to one recipient
                emailService.sendEmailWithAttachment(to, subject, body, attachmentPath);
            } else {
                // Invalid input, can't process this request
                return ResponseEntity.status(400).body(false);
            }

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }
    
    @PostMapping("/sendWithExternalApi")
    public ResponseEntity<Boolean> sendEmailWithExternalApi() {
        try {
            // Define the external API URL
            String externalApiUrl = "http://localhost:8080/api/control-testing-phase/email";

            // Make a GET call to the external API
            String response = restTemplate.getForObject(externalApiUrl, String.class);

            // Handle the response from the external API
            System.out.println("Response from external API: " + response);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(500).body(false);
        }
    }
  
}