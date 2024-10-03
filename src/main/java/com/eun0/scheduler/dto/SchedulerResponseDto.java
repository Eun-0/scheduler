package com.eun0.scheduler.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String writer;
    private String schedule;
    private Timestamp modificationDate;
}
