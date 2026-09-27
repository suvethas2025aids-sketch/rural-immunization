package com.clinic.ruralimmunization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_claims")
public class InsuranceClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccination_record_id", nullable = false)
    @NotNull(message = "Vaccination record is required")
    private VaccinationRecord vaccinationRecord;

    @ManyToOne(optional = false)
    @JoinColumn(name = "insurance_agency_id", nullable = false)
    @NotNull(message = "Insurance agency is required")
    private InsuranceAgency insuranceAgency;

    @NotNull(message = "Claim amount is required")
    @PositiveOrZero(message = "Claim amount cannot be negative")
    private BigDecimal claimAmount;

    private LocalDate claimDate;

    private String status = "PENDING";

    public InsuranceClaim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaccinationRecord getVaccinationRecord() {
        return vaccinationRecord;
    }

    public void setVaccinationRecord(VaccinationRecord vaccinationRecord) {
        this.vaccinationRecord = vaccinationRecord;
    }

    public InsuranceAgency getInsuranceAgency() {
        return insuranceAgency;
    }

    public void setInsuranceAgency(InsuranceAgency insuranceAgency) {
        this.insuranceAgency = insuranceAgency;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
