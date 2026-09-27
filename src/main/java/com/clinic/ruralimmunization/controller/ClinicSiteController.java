package com.clinic.ruralimmunization.controller;

import com.clinic.ruralimmunization.entity.ClinicSite;
import com.clinic.ruralimmunization.service.ClinicSiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinic-sites")
public class ClinicSiteController {

    private final ClinicSiteService clinicSiteService;

    public ClinicSiteController(ClinicSiteService clinicSiteService) {
        this.clinicSiteService = clinicSiteService;
    }

    @PostMapping
    public ClinicSite createClinicSite(
            @RequestBody ClinicSite clinicSite) {
        return clinicSiteService.createClinicSite(clinicSite);
    }

    @GetMapping
    public List<ClinicSite> getAllClinicSites() {
        return clinicSiteService.getAllClinicSites();
    }
}
