package com.eun0.scheduler.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleRequestDto {
    private String writer;
    private String password;
    private String todo;
}
