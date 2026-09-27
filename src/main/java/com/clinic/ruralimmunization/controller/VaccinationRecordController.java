package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.entity.VaccinationRecord;
import com.clinic.ruralimmunization.service.VaccinationRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccination-records")
public class VaccinationRecordController {

    private final VaccinationRecordService recordService;

    public VaccinationRecordController(
            VaccinationRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public VaccinationRecord addRecord(
            @Valid @RequestBody VaccinationRecord record) {
        return recordService.addRecord(record);
    }

    @GetMapping
    public List<VaccinationRecord> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public VaccinationRecord getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }
}