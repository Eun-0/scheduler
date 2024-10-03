package com.eun0.scheduler.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class SchedulerRequestDto {
    private String writer;
    private String password;
    private String schedule;
    private Timestamp modificationDate;
}
