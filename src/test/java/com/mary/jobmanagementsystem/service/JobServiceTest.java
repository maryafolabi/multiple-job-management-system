package com.mary.jobmanagementsystem.service;

import com.mary.jobmanagementsystem.JobManagementSystemApplication;
import com.mary.jobmanagementsystem.domain.Priority;
import com.mary.jobmanagementsystem.jobs.EmailSendingJob;
import com.mary.jobmanagementsystem.jobs.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JobManagementSystemApplication.class)
public class JobServiceTest {
    @Autowired
    JobService jobService;

    EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.LOW);

    List<Job> jobQueue = new ArrayList<>();

    @Test
    void testAddJob() {
        jobService.addJob(emailSendingJob);
        jobQueue = jobService.getJobQueue();

        assertEquals(1, jobQueue.size());

    }

    @Test
    void testGetJobQueue() {
        jobQueue = jobService.getJobQueue();
        assertEquals(0, jobQueue.size());
    }
}
