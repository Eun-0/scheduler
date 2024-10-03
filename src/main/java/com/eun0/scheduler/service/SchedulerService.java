package com.eun0.scheduler.service;

import com.eun0.scheduler.dto.ScheduleRequestDto;
import com.eun0.scheduler.dto.ScheduleResponseDto;
import com.eun0.scheduler.entity.Schedule;
import com.eun0.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {
    private SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = schedulerRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto responseDto = new ScheduleResponseDto(saveSchedule);

        return responseDto;
    }

    public List<ScheduleResponseDto> getSchedules() {
        // DB 조회
        return schedulerRepository.findAll();
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 스케줄이 DB에 존재하는지 확인
        // 비밀번호 일치 여부 확인
        Schedule schedule = schedulerRepository.findById(id, requestDto.getPassword());
        if(schedule != null) {
            // schedule 내용 수정
            schedulerRepository.update(id, requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 스케쥴은 존재하지 않습니다.");
        }
    }
}
