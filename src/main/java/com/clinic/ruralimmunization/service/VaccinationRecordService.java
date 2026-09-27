package com.clinic.ruralimmunization.service;

import com.clinic.ruralimmunization.entity.ClinicSite;
import com.clinic.ruralimmunization.entity.Patient;
import com.clinic.ruralimmunization.entity.VaccinationRecord;
import com.clinic.ruralimmunization.entity.VaccineBatch;
import com.clinic.ruralimmunization.repository.ClinicSiteRepository;
import com.clinic.ruralimmunization.repository.PatientRepository;
import com.clinic.ruralimmunization.repository.VaccinationRecordRepository;
import com.clinic.ruralimmunization.repository.VaccineBatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccinationRecordService {

    private final VaccinationRecordRepository recordRepository;
    private final PatientRepository patientRepository;
    private final VaccineBatchRepository batchRepository;
    private final ClinicSiteRepository clinicSiteRepository;

    public VaccinationRecordService(
            VaccinationRecordRepository recordRepository,
            PatientRepository patientRepository,
            VaccineBatchRepository batchRepository,
            ClinicSiteRepository clinicSiteRepository) {
        this.recordRepository = recordRepository;
        this.patientRepository = patientRepository;
        this.batchRepository = batchRepository;
        this.clinicSiteRepository = clinicSiteRepository;
    }

    @Transactional
    public VaccinationRecord addRecord(VaccinationRecord record) {

        Long patientId = record.getPatient().getId();
        Long batchId = record.getVaccineBatch().getId();
        Long clinicSiteId = record.getClinicSite().getId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found: " + patientId));

        VaccineBatch batch = batchRepository.findById(batchId)
                .orElseThrow(() ->
                        new RuntimeException("Vaccine batch not found: " + batchId));

        ClinicSite clinicSite = clinicSiteRepository.findById(clinicSiteId)
                .orElseThrow(() ->
                        new RuntimeException("Clinic site not found: " + clinicSiteId));

        int doses = record.getDosesAdministered();

        if (batch.getQuantity() < doses) {
            throw new RuntimeException("Not enough vaccine stock available");
        }

        batch.setQuantity(batch.getQuantity() - doses);

        record.setPatient(patient);
        record.setVaccineBatch(batch);
        record.setClinicSite(clinicSite);

        batchRepository.save(batch);
        return recordRepository.save(record);
    }

    public List<VaccinationRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    public VaccinationRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vaccination record not found with id: " + id));
    }
}