package com.eun0.scheduler.controller;

import com.eun0.scheduler.dto.ScheduleRequestDto;
import com.eun0.scheduler.dto.ScheduleResponseDto;
import com.eun0.scheduler.entity.Schedule;
import com.eun0.scheduler.service.SchedulerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    // CREATE
    @PostMapping("/scheduler")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return schedulerService.createSchedule(requestDto);
    }

    // READ
    @GetMapping("/scheduler")
    public List<ScheduleResponseDto> getSchedules() {
        return schedulerService.getSchedules();
    }

    // UPDATE
    @PutMapping("/scheduler/{schedulerId}")
    public Long updateSchedule(@PathVariable("schedulerId") Long id, @RequestBody ScheduleRequestDto requestDto) {
        return schedulerService.updateSchedule(id, requestDto);
    }
}
