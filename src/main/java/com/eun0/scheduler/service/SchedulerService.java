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
}
