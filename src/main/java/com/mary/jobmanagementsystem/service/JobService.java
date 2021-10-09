package com.mary.jobmanagementsystem.service;

import com.mary.jobmanagementsystem.domain.State;
import com.mary.jobmanagementsystem.jobs.Job;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

@Service
@Getter
@Setter
public class JobService {
    Timer timer = new Timer();

    List<Job> jobQueue = new ArrayList<>();

    public void addJob(Job job){
        // Change job state to "QUEUED" after adding to the queue
        job.setState(State.QUEUED);
        jobQueue.add(job);
    }

    public void runJobs(){
        Collections.sort(jobQueue);

        for (Job job : jobQueue) {
            if (job.getScheduledTime() != null){
                // Run this job at the specified scheduled time
                timer.schedule(job, job.getScheduledTime());
            }
            else {
                job.run();
            }
        }
    }
}
