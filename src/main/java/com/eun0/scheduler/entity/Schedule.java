package com.eun0.scheduler.entity;

import com.eun0.scheduler.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;                        // 고유 식별자
    private String writer;                  // 작성자명
    private String password;                // 비밀번호
    private String todo;                    // 할 일
    private Timestamp createdDate;          // 최초 작성일
    private Timestamp updatedDate;          // 수정일 (마지막 작성일)

    public Schedule(ScheduleRequestDto requestDto) {
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.todo = requestDto.getTodo();
    }

}
