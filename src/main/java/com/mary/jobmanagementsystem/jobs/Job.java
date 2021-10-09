package com.mary.jobmanagementsystem.jobs;

import com.mary.jobmanagementsystem.domain.Priority;
import com.mary.jobmanagementsystem.domain.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.TimerTask;

@Getter
@Setter
public abstract class Job extends TimerTask implements Comparable<Job>{

    State state;
    Date scheduledTime;
    private Priority priority;

    public Job(Priority priority) {
        this.priority = priority;
    }

    public abstract void run();

    @Override
    public int compareTo(Job job) {
        if(priority.getPriorityValue() < job.priority.getPriorityValue())
            return 1;
        else if(priority.getPriorityValue() > job.priority.getPriorityValue())
            return -1;
        return 0;
    }

}
