package com.clinic.ruralimmunization.repository;

import com.clinic.ruralimmunization.entity.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRecordRepository
        extends JpaRepository<VaccinationRecord, Long> {
}
