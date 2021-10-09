package com.mary.jobmanagementsystem.jobs;

import com.mary.jobmanagementsystem.domain.Priority;
import com.mary.jobmanagementsystem.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSendingJob extends Job{

    Logger log = LoggerFactory.getLogger(EmailSendingJob.class);

    public EmailSendingJob(Priority priority) {
        super(priority);
    }

    @Override
    public void run() {
        setState(State.RUNNING);
        log.info("EMAIL SENDING JOB STATE IN RUN METHOD: {}", getState());
        log.info("Email Sending Job is Running...");
        // Set recipient email address here
        sendMail("recipientemail@test.com");
    }

    public void sendMail(String recipient) {
        log.info("Sending email...");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Set your sender's email address and password here.
        String senderEmailAddress = "senderemail@test.com";
        String senderPassword = "password";

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmailAddress, senderPassword);
            }
        });

        Message message = prepareMessage(session, senderEmailAddress, recipient);

        if (message != null) {
            try {
                Transport.send(message);
                setState(State.SUCCESS);
                log.info("Email sent successfully.");
            } catch (MessagingException e) {
                setState(State.FAILED);
                log.info("Email failed to send.");
                e.printStackTrace();
            }
        }

    }

    private static Message prepareMessage(Session session, String myEmail, String recipient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Job Management System");
            message.setText("Hey there! I'm so excited you got this email. This email verifies the fulfillment of the requirement for my Job Management System. Thank you for being a part of this!...");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
