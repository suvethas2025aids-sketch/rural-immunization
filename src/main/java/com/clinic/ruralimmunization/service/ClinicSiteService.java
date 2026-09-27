package com.clinic.ruralimmunization.service;

import com.clinic.ruralimmunization.entity.ClinicSite;
import com.clinic.ruralimmunization.repository.ClinicSiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicSiteService {

    private final ClinicSiteRepository clinicSiteRepository;

    public ClinicSiteService(ClinicSiteRepository clinicSiteRepository) {
        this.clinicSiteRepository = clinicSiteRepository;
    }

    public ClinicSite createClinicSite(ClinicSite clinicSite) {
        return clinicSiteRepository.save(clinicSite);
    }

    public List<ClinicSite> getAllClinicSites() {
        return clinicSiteRepository.findAll();
    }
}
