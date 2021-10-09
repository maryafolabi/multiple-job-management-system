package com.mary.jobmanagementsystem;

import com.mary.jobmanagementsystem.domain.Priority;
import com.mary.jobmanagementsystem.jobs.EmailSendingJob;
import com.mary.jobmanagementsystem.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobManagementSystemApplication.class, args);

        Logger log = LoggerFactory.getLogger(JobManagementSystemApplication.class);

        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.HIGH);

        JobService jobService = new JobService();
        jobService.addJob(emailSendingJob);

        log.info("EMAIL SENDING JOB STATE BEFORE RUN METHOD: {}", emailSendingJob.getState());

        jobService.runJobs();

        log.info("EMAIL SENDING JOB STATE AFTER RUN METHOD: {}", emailSendingJob.getState());
    }

}
