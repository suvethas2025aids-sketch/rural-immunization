package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.entity.InsuranceAgency;
import com.clinic.ruralimmunization.service.InsuranceAgencyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance-agencies")
public class InsuranceAgencyController {

    private final InsuranceAgencyService insuranceAgencyService;

    public InsuranceAgencyController(
            InsuranceAgencyService insuranceAgencyService) {
        this.insuranceAgencyService = insuranceAgencyService;
    }

    @PostMapping
    public InsuranceAgency addAgency(
            @Valid @RequestBody InsuranceAgency agency) {
        return insuranceAgencyService.addAgency(agency);
    }

    @GetMapping
    public List<InsuranceAgency> getAllAgencies() {
        return insuranceAgencyService.getAllAgencies();
    }

    @GetMapping("/{id}")
    public InsuranceAgency getAgencyById(@PathVariable Long id) {
        return insuranceAgencyService.getAgencyById(id);
    }
}