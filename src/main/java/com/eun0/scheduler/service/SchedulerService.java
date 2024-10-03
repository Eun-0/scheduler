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

    public List<ScheduleResponseDto> readAllSchedules() {
        // DB 조회
        return schedulerRepository.readAll();
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 스케줄이 DB에 존재하는지 확인
        Schedule schedule = schedulerRepository.findById(id);

        if(schedule != null) {
            // 비밀번호 일치 여부 확인
            if(!schedule.getPassword().equals(requestDto.getPassword())) {
                return -2L;     // -2: 비밀번호 불일치
            } else {
                // schedule 내용 수정
                schedulerRepository.update(id, requestDto);
                return id;
            }
        } else {
            return -1L;         // -1: 해당 스케줄이 DB에 존재하지 않음
        }
    }

    public ScheduleResponseDto readSchedule(Long id) {
        // 해당 스케줄이 DB에 존재하는지 확인
        Schedule readSchedule = schedulerRepository.findById(id);

        // Entity -> ResponseDto
        ScheduleResponseDto responseDto = new ScheduleResponseDto(readSchedule);

        return responseDto;
    }

    public Long deleteSchedule(Long id, String password) {
        // 해당 스케줄이 DB에 존재하는지 확인
        Schedule schedule = schedulerRepository.findById(id);

        if(schedule != null) {
            // 비밀번호 일치 여부 확인
            if(!schedule.getPassword().equals(password)) {
                return -2L;     // -2: 비밀번호 불일치
            } else {
                // schedule 내용 수정
                schedulerRepository.delete(id);
                return id;
            }
        } else {
            return -1L;         // -1: 해당 스케줄이 DB에 존재하지 않음
        }
    }
}
