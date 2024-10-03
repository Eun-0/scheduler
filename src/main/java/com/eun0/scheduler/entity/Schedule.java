package com.eun0.scheduler.entity;

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
    private Timestamp creationDate;         // 최초 작성일
    private Timestamp modificationDate;     // 수정일 (마지막 작성일)
}
