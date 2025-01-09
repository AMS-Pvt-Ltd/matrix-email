package com.matrix.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.List;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            // Create a MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set basic email properties
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);  // true indicates HTML content

            // Send email
            mailSender.send(message);
            logger.info("Email sent successfully to {}", to);

        } catch (MailException | MessagingException e) {
            logger.error("Failed to send email to {}: {}", to, e.getMessage());
            // Optionally, you can throw a custom exception here if needed
            throw new RuntimeException("Email sending failed", e);
        }
    }

    // Method to send email to multiple recipients
    public void sendEmailToMultiple(List<String> toList, String subject, String body) {
        try {
            // Create a MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set multiple recipients
            helper.setTo(toList.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(body, true);

            // Send email
            mailSender.send(message);
            logger.info("Email sent successfully to {}", String.join(", ", toList));

        } catch (MailException | MessagingException e) {
            logger.error("Failed to send email to {}: {}", String.join(", ", toList), e.getMessage());
            throw new RuntimeException("Email sending failed", e);
        }
    }

    // Method to send email with attachments
    public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
        try {
            // Create a MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set basic email properties
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);  // true indicates HTML content

            // Add attachment
            helper.addAttachment("attachment", new java.io.File(attachmentPath));

            // Send email
            mailSender.send(message);
            logger.info("Email with attachment sent successfully to {}", to);

        } catch (MailException | MessagingException e) {
            logger.error("Failed to send email with attachment to {}: {}", to, e.getMessage());
            throw new RuntimeException("Email sending with attachment failed", e);
        }
    }
    
    
}
