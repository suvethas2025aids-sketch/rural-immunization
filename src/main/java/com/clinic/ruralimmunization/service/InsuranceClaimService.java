package com.clinic.ruralimmunization.service;

import com.clinic.ruralimmunization.entity.InsuranceAgency;
import com.clinic.ruralimmunization.entity.InsuranceClaim;
import com.clinic.ruralimmunization.entity.VaccinationRecord;
import com.clinic.ruralimmunization.repository.InsuranceAgencyRepository;
import com.clinic.ruralimmunization.repository.InsuranceClaimRepository;
import com.clinic.ruralimmunization.repository.VaccinationRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class InsuranceClaimService {

    private final InsuranceClaimRepository claimRepository;
    private final VaccinationRecordRepository recordRepository;
    private final InsuranceAgencyRepository agencyRepository;

    private static final Set<String> VALID_STATUSES =
            Set.of("PENDING", "SUBMITTED", "APPROVED", "REJECTED");

    public InsuranceClaimService(
            InsuranceClaimRepository claimRepository,
            VaccinationRecordRepository recordRepository,
            InsuranceAgencyRepository agencyRepository) {
        this.claimRepository = claimRepository;
        this.recordRepository = recordRepository;
        this.agencyRepository = agencyRepository;
    }

    @Transactional
    public InsuranceClaim createClaim(InsuranceClaim claim) {

        if (claim.getVaccinationRecord() == null
                || claim.getVaccinationRecord().getId() == null) {
            throw new RuntimeException("Vaccination record ID is required");
        }

        if (claim.getInsuranceAgency() == null
                || claim.getInsuranceAgency().getId() == null) {
            throw new RuntimeException("Insurance agency ID is required");
        }

        Long recordId = claim.getVaccinationRecord().getId();
        Long agencyId = claim.getInsuranceAgency().getId();

        VaccinationRecord record = recordRepository.findById(recordId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vaccination record not found: " + recordId));

        InsuranceAgency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Insurance agency not found: " + agencyId));

        claim.setVaccinationRecord(record);
        claim.setInsuranceAgency(agency);

        if (claim.getClaimDate() == null) {
            claim.setClaimDate(LocalDate.now());
        }

        if (claim.getStatus() == null || claim.getStatus().isBlank()) {
            claim.setStatus("PENDING");
        } else {
            claim.setStatus(claim.getStatus().toUpperCase());
        }

        if (!VALID_STATUSES.contains(claim.getStatus())) {
            throw new RuntimeException("Invalid claim status");
        }

        return claimRepository.save(claim);
    }

    public List<InsuranceClaim> getAllClaims() {
        return claimRepository.findAll();
    }

    public InsuranceClaim getClaimById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Insurance claim not found: " + id));
    }

    @Transactional
    public InsuranceClaim updateClaimStatus(Long id, String status) {

        if (status == null || status.isBlank()) {
            throw new RuntimeException("Claim status is required");
        }

        String newStatus = status.toUpperCase();

        if (!VALID_STATUSES.contains(newStatus)) {
            throw new RuntimeException(
                    "Invalid status. Use PENDING, SUBMITTED, APPROVED, or REJECTED");
        }

        InsuranceClaim claim = getClaimById(id);
        claim.setStatus(newStatus);

        return claimRepository.save(claim);
    }
}