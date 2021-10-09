package com.mary.jobmanagementsystem.domain;

import lombok.Getter;

@Getter
public enum Priority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int priorityValue;

    Priority(int priorityValue) {
        this.priorityValue = priorityValue;
    }
}
