package com.clinic.ruralimmunization.service;

import com.clinic.ruralimmunization.entity.InsuranceAgency;
import com.clinic.ruralimmunization.repository.InsuranceAgencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceAgencyService {

    private final InsuranceAgencyRepository insuranceAgencyRepository;

    public InsuranceAgencyService(
            InsuranceAgencyRepository insuranceAgencyRepository) {
        this.insuranceAgencyRepository = insuranceAgencyRepository;
    }

    public InsuranceAgency addAgency(InsuranceAgency agency) {
        return insuranceAgencyRepository.save(agency);
    }

    public List<InsuranceAgency> getAllAgencies() {
        return insuranceAgencyRepository.findAll();
    }

    public InsuranceAgency getAgencyById(Long id) {
        return insuranceAgencyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Insurance agency not found with id: " + id));
    }
}