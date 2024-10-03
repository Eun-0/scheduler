package com.eun0.scheduler.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Scheduler {
    private Long id;
    private String writer;
    private String password;
    private String schedule;
    private Timestamp creationDate;
    private Timestamp modificationDate;
}
