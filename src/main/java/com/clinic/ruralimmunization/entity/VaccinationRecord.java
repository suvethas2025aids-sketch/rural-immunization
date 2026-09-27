package com.clinic.ruralimmunization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "vaccination_records")
public class VaccinationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccine_batch_id", nullable = false)
    @NotNull(message = "Vaccine batch is required")
    private VaccineBatch vaccineBatch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clinic_site_id", nullable = false)
    @NotNull(message = "Clinic site is required")
    private ClinicSite clinicSite;

    @NotNull(message = "Dose count is required")
    @Min(value = 1, message = "At least one dose must be administered")
    private Integer dosesAdministered;

    @NotNull(message = "Administration date is required")
    private LocalDate administeredDate;

    public VaccinationRecord() {
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public VaccineBatch getVaccineBatch() {
        return vaccineBatch;
    }

    public void setVaccineBatch(VaccineBatch vaccineBatch) {
        this.vaccineBatch = vaccineBatch;
    }

    public ClinicSite getClinicSite() {
        return clinicSite;
    }

    public void setClinicSite(ClinicSite clinicSite) {
        this.clinicSite = clinicSite;
    }

    public Integer getDosesAdministered() {
        return dosesAdministered;
    }

    public void setDosesAdministered(Integer dosesAdministered) {
        this.dosesAdministered = dosesAdministered;
    }

    public LocalDate getAdministeredDate() {
        return administeredDate;
    }

    public void setAdministeredDate(LocalDate administeredDate) {
        this.administeredDate = administeredDate;
    }
}
