package com.eun0.scheduler.controller;

import com.eun0.scheduler.dto.ScheduleRequestDto;
import com.eun0.scheduler.dto.ScheduleResponseDto;
import com.eun0.scheduler.entity.Schedule;
import com.eun0.scheduler.service.SchedulerService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    // CREATE
    @PostMapping("")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return schedulerService.createSchedule(requestDto);
    }

    // READ
    @GetMapping("")
    public List<ScheduleResponseDto> readAllSchedules() {
        return schedulerService.readAllSchedules();
    }

    // READ
    @GetMapping("/{schedulerId}")
    public ScheduleResponseDto readSchedule(@PathVariable("schedulerId") Long id) {
        return schedulerService.readSchedule(id);
    }

    // UPDATE
    @PutMapping("/{schedulerId}")
    public Long updateSchedule(@PathVariable("schedulerId") Long id, @RequestBody ScheduleRequestDto requestDto) {
        return schedulerService.updateSchedule(id, requestDto);
    }

    // DELETE
    @DeleteMapping("/{schedulerId}")
    public Long deleteSchedule(@PathVariable("schedulerId") Long id, @RequestBody HashMap<String, String> request) {
        return schedulerService.deleteSchedule(id, request.get("password"));
    }
}
