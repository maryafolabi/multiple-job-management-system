package com.mary.jobmanagementsystem.domain;

import com.mary.jobmanagementsystem.JobManagementSystemApplication;
import com.mary.jobmanagementsystem.jobs.EmailSendingJob;
import com.mary.jobmanagementsystem.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JobManagementSystemApplication.class)
public class JobTest {
    @Autowired
    JobService jobService;

    @Test
    void getNullState() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.MEDIUM);
        State state = emailSendingJob.getState();
        assertNull(state);
    }

    @Test
    void getNonNullState() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.MEDIUM);
        jobService.addJob(emailSendingJob);
        State state = emailSendingJob.getState();
        assertNotNull(state);
        assertEquals(State.QUEUED, state);
    }

    @Test
    void testJobRunMethodNotQUEUED() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.MEDIUM);
        jobService.addJob(emailSendingJob);
        emailSendingJob.run();
        State state = emailSendingJob.getState();
        assertNotNull(state);
        assertNotEquals(State.QUEUED, state);
    }

    @Test
    void testJobRunMethodNotNULL() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.MEDIUM);
        jobService.addJob(emailSendingJob);
        emailSendingJob.run();
        State state = emailSendingJob.getState();
        assertNotNull(state);
        assertNotEquals(null, state);
    }

    @Test
    void setState() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.HIGH);
        emailSendingJob.setState(State.FAILED);
        assertEquals(State.FAILED, emailSendingJob.getState());
    }
}
