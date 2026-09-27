package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.dto.AdministerRequest;
import com.clinic.ruralimmunization.entity.VaccineBatch;
import com.clinic.ruralimmunization.service.VaccineBatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    private final VaccineBatchService batchService;

    public AdministrationController(VaccineBatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/administer")
    public VaccineBatch administerDoses(
            @RequestBody AdministerRequest request) {
        return batchService.administerDoses(request);
    }
}