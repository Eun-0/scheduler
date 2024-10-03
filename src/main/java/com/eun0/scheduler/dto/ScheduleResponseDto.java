package com.eun0.scheduler.dto;

import com.eun0.scheduler.entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String writer;
    private String todo;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.writer = schedule.getWriter();
        this.todo = schedule.getTodo();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }

    public ScheduleResponseDto(Long id, String writer, String todo, Timestamp createdDate, Timestamp updatedDate) {
        this.id = id;
        this.writer = writer;
        this.todo = todo;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
