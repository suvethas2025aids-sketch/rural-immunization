package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.entity.VaccineBatch;
import com.clinic.ruralimmunization.service.VaccineBatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineBatchController {

    private final VaccineBatchService batchService;

    public VaccineBatchController(VaccineBatchService batchService) {
        this.batchService = batchService;
    }

    // Register a vaccine batch
    @PostMapping("/batches")
    public VaccineBatch createBatch(@RequestBody VaccineBatch batch) {
        return batchService.createBatch(batch);
    }

    // View all vaccine batches
    @GetMapping("/batches")
    public List<VaccineBatch> getAllBatches() {
        return batchService.getAllBatches();
    }

    // Find batches expiring within the next 30 days by default
    @GetMapping("/expiring")
    public List<VaccineBatch> getExpiringBatches(
            @RequestParam(defaultValue = "30") int withinDays) {
        return batchService.getExpiringBatches(withinDays);
    }
}