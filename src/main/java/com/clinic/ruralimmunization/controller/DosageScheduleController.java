package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.entity.DosageSchedule;
import com.clinic.ruralimmunization.service.DosageScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dosage-schedules")
public class DosageScheduleController {

    private final DosageScheduleService service;

    public DosageScheduleController(DosageScheduleService service) {
        this.service = service;
    }

    // Create a dosage schedule
    @PostMapping
    public DosageSchedule createSchedule(
            @RequestBody DosageSchedule schedule) {
        return service.createSchedule(schedule);
    }

    // Get all dosage schedules
    @GetMapping
    public List<DosageSchedule> getAllSchedules() {
        return service.getAllSchedules();
    }

    // Update a dosage schedule
    @PutMapping("/{id}")
    public DosageSchedule updateSchedule(
            @PathVariable Long id,
            @RequestBody DosageSchedule schedule) {
        return service.updateSchedule(id, schedule);
    }

    // Delete a dosage schedule
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        service.deleteSchedule(id);
    }
}