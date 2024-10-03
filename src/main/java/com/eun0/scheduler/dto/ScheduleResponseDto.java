package com.eun0.scheduler.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String writer;
    private String todo;
    private Timestamp modificationDate;
}
