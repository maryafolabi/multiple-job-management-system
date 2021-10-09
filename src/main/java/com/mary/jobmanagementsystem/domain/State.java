package com.mary.jobmanagementsystem.domain;

import lombok.Getter;

@Getter
public enum State {
    QUEUED,
    RUNNING,
    SUCCESS,
    FAILED
}

