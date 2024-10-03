package com.eun0.scheduler.repository;

import com.eun0.scheduler.dto.ScheduleRequestDto;
import com.eun0.scheduler.dto.ScheduleResponseDto;
import com.eun0.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class SchedulerRepository {
    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO scheduler (writer, password, todo) VALUES (?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getWriter());
                    preparedStatement.setString(2, schedule.getPassword());
                    preparedStatement.setString(3, schedule.getTodo());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM scheduler";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Scheduler 데이터들을 ScheduleResponseDto 타입으로 변환해 줄 메서드
                Long id = rs.getLong("scheduler_id");
                String writer = rs.getString("writer");
                String todo = rs.getString("todo");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                return new ScheduleResponseDto(id, writer, todo, createdDate, updatedDate);
            }
        });
    }

    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE scheduler SET writer = ?, todo = ? WHERE scheduler_id = ?";
        jdbcTemplate.update(sql, requestDto.getWriter(), requestDto.getTodo(), id);
    }

    public Schedule findById(Long id, String password) {
        // DB 조회
        String sql = "SELECT * FROM scheduler WHERE scheduler_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                // 비밀번호 확인
                String dbPassword = resultSet.getString("password");
                // 비밀번호 불일치
                if (!dbPassword.equals(password)) {
                    throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                }

                // 비밀번호 일치
                Schedule schedule = new Schedule();
                schedule.setWriter(resultSet.getString("writer"));
                schedule.setTodo(resultSet.getString("todo"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
